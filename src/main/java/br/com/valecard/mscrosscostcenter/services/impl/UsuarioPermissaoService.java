package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoId;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioPermissaoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface UsuarioPermissaoService {

    UsuarioPermissaoEntity findById(UsuarioPermissaoId id);

    List<UsuarioPermissaoEntity> findAll(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException;

    UsuarioPermissaoEntity save(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException;

    UsuarioPermissaoEntity update(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException;

    UsuarioPermissaoEntity delete(UsuarioPermissaoId id) throws ValidationException;

    UsuarioPermissaoEntity validate(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException;
}