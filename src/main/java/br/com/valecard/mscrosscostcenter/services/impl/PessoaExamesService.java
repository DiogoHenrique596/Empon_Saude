package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesId;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaExamesDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface PessoaExamesService {


    List<PessoaExamesEntity> findAll() throws ValidationException;

    PessoaExamesEntity save(PessoaExamesDTO dto)  throws ValidationException;

    boolean deleteByDto( PessoaExamesDTO pessoaExamesDTO) throws ValidationException;

    List<PessoaExamesEntity> findByPessoaFisicaId(Integer pessoaFisica) throws ValidationException;

}
