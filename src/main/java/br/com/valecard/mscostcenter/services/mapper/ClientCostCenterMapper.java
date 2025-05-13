package br.com.valecard.mscostcenter.services.mapper;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;

public class ClientCostCenterMapper {

    public static AdcClientCostCenterEntity toEntity( ClientCostCenterDTO clientCostCenterDTO ) {
        if ( clientCostCenterDTO == null ) return null;

        AdcClientCostCenterEntity entity = new AdcClientCostCenterEntity();
        entity.setBranch( clientCostCenterDTO.getBranch() );
        entity.setCode( clientCostCenterDTO.getCode() );
        entity.setDescription( clientCostCenterDTO.getDescription() );
        entity.setCostCenter( clientCostCenterDTO.getCostCenter() );
        entity.setClientId( clientCostCenterDTO.getClientId() );
        entity.setResultCenterId( clientCostCenterDTO.getResultCenterId() );
        entity.setVirtual( clientCostCenterDTO.getVirtual() );
        return entity;
    }
}
