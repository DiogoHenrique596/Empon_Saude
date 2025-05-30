package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface UsuarioService {

    UsuarioEntity findById(Integer id);

    List<UsuarioEntity> findAll(UsuarioDTO usuarioDTO) throws ValidationException;

    UsuarioEntity save(UsuarioDTO usuarioDTO) throws ValidationException;

    UsuarioEntity validate(UsuarioDTO usuarioDTO) throws ValidationException;

    UsuarioEntity delete(Integer id) throws ValidationException;

    UsuarioEntity update(UsuarioDTO usuarioDTO) throws ValidationException;
}