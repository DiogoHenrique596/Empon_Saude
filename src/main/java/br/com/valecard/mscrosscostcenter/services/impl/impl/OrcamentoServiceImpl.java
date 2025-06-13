package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.OrcamentoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesId;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaJuridicaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.OrcamentoRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaExamesRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaJuridicaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.OrcamentoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.OrcamentoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrcamentoServiceImpl implements OrcamentoService {
    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    private PessoaExamesRepository pessoaExamesRepository;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;


    @Override
    public OrcamentoEntity findById(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID não pode ser nulo");
        }

        return orcamentoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Orçamento não encontrado para ID: " + id));
    }

    @Override
    public List<OrcamentoEntity> findAll() throws ValidationException {
        List<OrcamentoEntity> orcamentos = orcamentoRepository.findAll();
        if (orcamentos.isEmpty()) {
            throw new ValidationException("Nenhum orçamento encontrado");
        }
        return orcamentos;
    }

    @Override
    public OrcamentoEntity save(OrcamentoDTO orcamentoDTO) throws ValidationException {
        if (orcamentoDTO == null) {
            throw new ValidationException("Orçamento não pode ser nulo");
        }
        if (orcamentoDTO.getPessoaExames() == null || orcamentoDTO.getPessoaJuridica() == null) {
            throw new ValidationException("PessoaExames e PessoaJuridica não podem ser nulos");
        }
        OrcamentoEntity entity = toEntity(orcamentoDTO);
        return orcamentoRepository.save(entity);
    }

    @Override
    public OrcamentoEntity update(OrcamentoDTO orcamentoDTO) throws ValidationException {
        if (orcamentoDTO == null || orcamentoDTO.getId() == null) {
            throw new ValidationException("Orçamento não pode ser nulo e deve ter um ID válido");
        }
        OrcamentoEntity entity = orcamentoRepository.findById(orcamentoDTO.getId())
                .orElseThrow(() -> new ValidationException("Orçamento não encontrado para ID: " + orcamentoDTO.getId()));

        entity.setValor(orcamentoDTO.getValor());
        entity.setDataOrcamento(orcamentoDTO.getDataOrcamento());

        // Atualizar PessoaExames
        PessoaExamesId pessoaExamesId = new PessoaExamesId(
                orcamentoDTO.getPessoaExames().getPessoaId(),
                orcamentoDTO.getPessoaExames().getExameId(),
                orcamentoDTO.getPessoaExames().getDataCadastro()
        );
        PessoaExamesEntity pessoaExames = pessoaExamesRepository.findById(pessoaExamesId)
                .orElseThrow(() -> new ValidationException("PessoaExames não encontrada"));
        entity.setPessoaExames(pessoaExames);

        // Atualizar PessoaJuridica
        PessoaJuridicaEntity pessoaJuridica = pessoaJuridicaRepository.findById(
                orcamentoDTO.getPessoaJuridica().getId()
        ).orElseThrow(() -> new ValidationException("PessoaJuridica não encontrada"));
        entity.setPessoaJuridica(pessoaJuridica);

        return orcamentoRepository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID não pode ser nulo");
        }
        OrcamentoEntity orcamentoEntity = orcamentoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Orçamento não encontrado para ID: " + id));

        orcamentoRepository.delete(orcamentoEntity);
        return true;
    }

    private OrcamentoEntity toEntity(OrcamentoDTO dto) throws ValidationException {
        OrcamentoEntity entity = new OrcamentoEntity();
        entity.setId(dto.getId());

        // Buscar PessoaExamesEntity pela chave composta
        PessoaExamesId pessoaExamesId = new PessoaExamesId(
                dto.getPessoaExames().getPessoaId(),
                dto.getPessoaExames().getExameId(),
                dto.getPessoaExames().getDataCadastro()
        );
        PessoaExamesEntity pessoaExames = pessoaExamesRepository.findById(pessoaExamesId)
                .orElseThrow(() -> new ValidationException("PessoaExames não encontrada"));

        // Buscar PessoaJuridicaEntity pelo CNPJ
        PessoaJuridicaEntity pessoaJuridica = pessoaJuridicaRepository.findById(
                dto.getPessoaJuridica().getId()
        ).orElseThrow(() -> new ValidationException("PessoaJuridica não encontrada"));
        entity.setPessoaExames(pessoaExames);
        entity.setPessoaJuridica(pessoaJuridica);
        entity.setValor(dto.getValor());
        entity.setDataOrcamento(dto.getDataOrcamento());

        return entity;
    }
}
