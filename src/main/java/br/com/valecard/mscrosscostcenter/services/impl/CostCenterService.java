package br.com.valecard.mscrosscostcenter.services.impl;

import org.springframework.data.domain.Page;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface CostCenterService {

    AdcClientCostCenterEntity findById( Long id );

    AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException;

    AdcClientCostCenterEntity validate( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException;

    List<AdcClientCostCenterEntity> persistList( List<ClientCostCenterDTO> clientCostCenterDTO ) throws ValidationException;

    AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException;

    AdcClientCostCenterEntity findByClientIdSeedData( Long clientId ) throws ValidationException;

}
