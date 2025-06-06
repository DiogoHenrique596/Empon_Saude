package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PermissaoEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PermissaoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PermissaoService {

    PermissaoEntity findById(Integer id);

    List<PermissaoEntity> findAll() throws ValidationException;

    PermissaoEntity save(PermissaoDTO permissaoDTO) throws ValidationException;

    PermissaoEntity update(PermissaoDTO permissaoDTO) throws ValidationException;

    PermissaoEntity delete(Integer id) throws ValidationException;
}