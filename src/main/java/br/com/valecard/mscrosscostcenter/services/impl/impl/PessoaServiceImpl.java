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
    public List<PessoaEntity> findAll(PessoaDTO pessoaDTO) throws ValidationException {
        return pessoaRepository.findAll();
    }

    @Override
    public PessoaEntity save(PessoaDTO pessoaDTO) throws ValidationException {
        validarPessoaDTO(pessoaDTO);
        PessoaEntity entity = toEntity(pessoaDTO);
        return pessoaRepository.save(entity);
    }

    @Override
    public PessoaEntity update(PessoaDTO pessoaDTO) throws ValidationException {
        Assert.notNull(pessoaDTO.getId(), "id da pessoa não pode ser nulo");
        PessoaEntity existente = pessoaRepository.findById(pessoaDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);
        validarPessoaDTO(pessoaDTO);

        if (pessoaDTO.getNome() != null) existente.setNome(pessoaDTO.getNome());
        if (pessoaDTO.getTipo() != null) existente.setTipo(pessoaDTO.getTipo());

        return pessoaRepository.save(existente);
    }

    @Override
    @Transactional
    public PessoaEntity delete(Integer id) throws ValidationException {
        Assert.notNull(id, "id não pode ser nulo");
        PessoaEntity entity = pessoaRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        pessoaRepository.delete(entity);
        return entity;
    }

    private void validarPessoaDTO(PessoaDTO pessoaDTO) throws ValidationException {
        List<String> erros = new ArrayList<>();
        if (pessoaDTO == null) {
            erros.add("PessoaDTO não pode ser nulo");
        } else {
            if (pessoaDTO.getNome() == null || pessoaDTO.getNome().trim().isEmpty()) {
                erros.add("Nome é obrigatório");
            }
        }
        if (!erros.isEmpty()) {
            throw new ValidationException(String.join(", ", erros));
        }
    }

    private PessoaEntity toEntity(PessoaDTO dto) {
        PessoaEntity entity = new PessoaEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setTipo(dto.getTipo());
        return entity;
    }
}