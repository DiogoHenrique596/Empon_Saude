package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity findById(Integer id);

    List<UsuarioEntity> findAll() throws ValidationException;

    UsuarioEntity save(UsuarioDTO usuarioDTO) throws ValidationException;

    boolean delete(Integer id) throws ValidationException;

    UsuarioEntity update(UsuarioDTO usuarioDTO) throws ValidationException;
}