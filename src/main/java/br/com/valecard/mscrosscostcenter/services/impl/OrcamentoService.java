package br.com.valecard.mscrosscostcenter.services.impl;

import br.com.valecard.mscrosscostcenter.db.entities.OrcamentoEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.OrcamentoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;
import java.util.Optional;

public interface OrcamentoService {

    OrcamentoEntity findById(Integer id) throws ValidationException;

    List<OrcamentoEntity> findAll() throws ValidationException;

    OrcamentoEntity save(OrcamentoDTO orcamentoDTO) throws ValidationException;

    OrcamentoEntity update(OrcamentoDTO orcamentoDTO) throws ValidationException;

    boolean deleteById(Integer id) throws ValidationException;
}
