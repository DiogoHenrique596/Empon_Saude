package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaJuridicaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaJuridicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;
import java.util.Optional;

public interface PessoaJuridicaService {



    PessoaJuridicaEntity findByCnpj(String cnpj) throws ValidationException;

    List<PessoaJuridicaEntity> findAll() throws ValidationException;

    PessoaJuridicaEntity save(PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException;

    PessoaJuridicaEntity update(PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException;

    boolean deleteByCnpj(String cnpj) throws ValidationException;
}
