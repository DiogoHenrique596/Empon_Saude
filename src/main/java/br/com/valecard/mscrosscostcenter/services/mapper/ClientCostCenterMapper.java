package br.com.valecard.mscrosscostcenter.services.mapper;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.services.dtos.ClientCostCenterDTO;

public class ClientCostCenterMapper {

    public static AdcClientCostCenterEntity toEntity( ClientCostCenterDTO clientCostCenterDTO ) {
        if ( clientCostCenterDTO == null ) return null;

        AdcClientCostCenterEntity entity = new AdcClientCostCenterEntity();
        entity.setId( clientCostCenterDTO.getId() );
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
