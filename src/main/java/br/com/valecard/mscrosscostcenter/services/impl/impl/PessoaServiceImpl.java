package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ResourceNotFoundException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public PessoaEntity findById(Integer id) {
        Assert.notNull(id, "id não pode ser nulo");
        return pessoaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<PessoaEntity> findAll() throws ValidationException {
        return pessoaRepository.findAll();
    }

    @Override
    public PessoaEntity save(PessoaDTO pessoaDTO) throws ValidationException {
        Assert.notNull(pessoaDTO, "pessoaDTO não pode ser nulo");
        PessoaEntity entity = toEntity(pessoaDTO);
        return pessoaRepository.save(entity);
    }

    @Override
    public PessoaEntity update(PessoaDTO pessoaDTO) throws ValidationException {
        Assert.notNull(pessoaDTO.getId(), "id da pessoa não pode ser nulo");
        PessoaEntity existente = pessoaRepository.findById(pessoaDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);


        if (pessoaDTO.getNome() != null) existente.setNome(pessoaDTO.getNome());
        if (pessoaDTO.getTipo() != null) existente.setTipo(pessoaDTO.getTipo());

        return pessoaRepository.save(existente);
    }

    @Override

    public boolean delete(Integer id) throws ValidationException {
        Assert.notNull(id, "id não pode ser nulo");
        PessoaEntity entity = pessoaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        pessoaRepository.delete(entity);
        return true;
    }


    private PessoaEntity toEntity(PessoaDTO dto) {
        PessoaEntity entity = new PessoaEntity();
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        entity.setDataCadastro(dto.getDataCadastro());
        entity.setTelefone(dto.getTelefone());
        entity.setTelefoneFixo(dto.getTelefoneFixo());
        return entity;
    }
}