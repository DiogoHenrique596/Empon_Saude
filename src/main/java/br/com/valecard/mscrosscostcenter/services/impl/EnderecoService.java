package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.EnderecoEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.EnderecoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface EnderecoService {

    List<EnderecoEntity> findByPessoaId(Integer pessoaId) throws ValidationException;

    List<EnderecoEntity> findAll() throws ValidationException;

    EnderecoEntity save(EnderecoDTO enderecoDTO) throws ValidationException;

    EnderecoEntity update(EnderecoDTO enderecoDTO) throws ValidationException;

    boolean deleteById(Integer id) throws ValidationException;
}
