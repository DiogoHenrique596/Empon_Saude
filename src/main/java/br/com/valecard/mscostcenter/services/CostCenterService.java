package br.com.valecard.mscostcenter.services;

import org.springframework.data.domain.Page;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;

public interface CostCenterService {

    AdcClientCostCenterEntity findById( Long id );

    AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException;

    AdcClientCostCenterEntity update( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException;

    void deleteById( Long id );

    Page<AdcClientCostCenterEntity> getCostCenterPaginated( Long clientId, Long productId, String field, String filter,
                                                            Integer pageNumber, Integer pageSize,
                                                            Long thirdPartyUserId );

    byte[] getImportTemplateFile( Long clientId, Long thirdPartyUserId );

    AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO, Long thirdPartyUserId );

}
