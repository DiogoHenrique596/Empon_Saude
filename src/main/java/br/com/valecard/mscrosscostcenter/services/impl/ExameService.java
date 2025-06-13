package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.ExameEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ExameDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface ExameService {

    ExameEntity findById(Integer id) throws ValidationException;

    List<ExameEntity> findAll() throws ValidationException;

    ExameEntity save(ExameDTO exameDTO) throws ValidationException;

    ExameEntity update(ExameDTO exameDTO) throws ValidationException;

    boolean deleteById(Integer id) throws ValidationException;
}
