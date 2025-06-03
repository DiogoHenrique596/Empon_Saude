package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface PessoaService {

    PessoaEntity findById(Integer id);

    List<PessoaEntity> findAll(PessoaDTO pessoaDTO) throws ValidationException;

    PessoaEntity save(PessoaDTO pessoaDTO) throws ValidationException;

    PessoaEntity update(PessoaDTO pessoaDTO) throws ValidationException;

    PessoaEntity delete(Integer id) throws ValidationException;
}