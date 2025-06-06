package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoId;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioPermissaoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface UsuarioPermissaoService {

    UsuarioPermissaoEntity findById(UsuarioPermissaoId id);

    List<UsuarioPermissaoEntity> findAll() throws ValidationException;

    UsuarioPermissaoEntity save(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException;

    boolean delete(UsuarioPermissaoId id) throws ValidationException;
}