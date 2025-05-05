package br.com.valecard.mscostcenter.services;

import org.springframework.data.domain.Page;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;

public interface CostCenterService {

    AdcClientCostCenterEntity findById( Long id, Long clientId );

    AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws ValidationException;

    AdcClientCostCenterEntity update( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws
            ValidationException;

    void deleteById( Long id, Long clientId );

    Page<AdcClientCostCenterEntity> getCostCenterPaginated( Long clientId, Long productId, String field, String filter,
                                                            Integer pageNumber, Integer pageSize );

    byte[] getImportTemplateFile( Long clientId );

    AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO, Long clientId );

}
