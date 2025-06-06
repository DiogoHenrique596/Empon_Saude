package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaFisicaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaFisicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface PessoaFisicaService {

    PessoaFisicaEntity findByCpf(String cpf) throws ValidationException;

    List<PessoaFisicaEntity> findAll() throws ValidationException;

    PessoaFisicaEntity save(PessoaFisicaDTO pessoaFisicaDTO) throws ValidationException;

    PessoaFisicaEntity update(PessoaFisicaDTO pessoaFisicaDTO) throws ValidationException;

    boolean deleteByCpf(String cpf) throws ValidationException;
}
