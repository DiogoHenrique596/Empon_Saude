package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.ExameEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesId;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaFisicaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaExamesRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaFisicaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaExamesService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaExamesDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaExamesServiceImpl implements PessoaExamesService {

    @Autowired
    private PessoaExamesRepository pessoaExamesRepository;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;



    @Override
    public List<PessoaExamesEntity> findAll() throws ValidationException {
        List<PessoaExamesEntity> pessoaExamesList = pessoaExamesRepository.findAll();
        if (pessoaExamesList.isEmpty()) {
            throw new ValidationException("Nenhum registro de PessoaExames encontrado");
        }
        return pessoaExamesList;
    }

    @Override
    public PessoaExamesEntity save(PessoaExamesDTO dto) throws ValidationException {
        if (dto == null) {
            throw new ValidationException("PessoaExamesDTO nao pode ser nulo");
        }
        if (dto.getPessoaId() == null || dto.getExameId() == null || dto.getDataCadastro() == null) {
            throw new ValidationException("PessoaId e ExameId nao podem ser nulos e DataCadastro deve ser informada");
        }

        PessoaExamesEntity entity = toEntity(dto);
        if (pessoaExamesRepository.existsById(entity.getId())) {
            throw new ValidationException("Já existe um exame cadastrado para esta pessoa e data.");
        }
        return pessoaExamesRepository.save(entity);
    }

    public boolean deleteByDto(PessoaExamesDTO dto) throws ValidationException {
        if (dto == null || dto.getPessoaId() == null || dto.getExameId() == null || dto.getDataCadastro() == null) {
            throw new ValidationException("Dados obrigatórios não informados");
        }
        PessoaExamesId id = new PessoaExamesId(dto.getPessoaId(), dto.getExameId(), dto.getDataCadastro());
        if (!pessoaExamesRepository.existsById(id)) {
            throw new ValidationException("Registro não encontrado para exclusão");
        }
        pessoaExamesRepository.deleteById(id);
        return true;

    }

    @Override
    public List<PessoaExamesEntity> findByPessoaFisicaId(Integer pessoaFisica) throws ValidationException {
        if (pessoaFisica == null) {
            throw new ValidationException("PessoaFisica nao pode ser nulo");
        }
        if (!pessoaFisicaRepository.existsById(pessoaFisica)) {
            throw new ValidationException("PessoaFisica nao encontrado para o ID: " + pessoaFisica);
        }
        return pessoaExamesRepository.findByPessoaFisicaId(pessoaFisica);
    }

    private PessoaExamesEntity toEntity(PessoaExamesDTO dto) throws ValidationException {

        PessoaExamesEntity entity = new PessoaExamesEntity();

        PessoaExamesId id = new PessoaExamesId();
        id.setPessoaFisica(dto.getPessoaId());
        id.setExames(dto.getExameId());
        if (dto.getDataCadastro() != null) {
            id.setDataCadastro(dto.getDataCadastro());
        }entity.setId(id);

        ExameEntity exame = new ExameEntity();
        exame.setId(dto.getExameId());
        entity.setExames(exame);

        PessoaFisicaEntity pessoaFisica = new PessoaFisicaEntity();
        pessoaFisica.setId(dto.getPessoaId());
        entity.setPessoaFisica(pessoaFisica);

        return entity;
    }

}
