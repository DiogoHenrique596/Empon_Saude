package br.com.valecard.mscrosscostcenter.services.impl;

import org.springframework.data.domain.Page;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

import java.util.List;

public interface CostCenterService {

    AdcClientCostCenterEntity findById( Long id, Long clientId );

    AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws ValidationException;

    AdcClientCostCenterEntity validate( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws ValidationException;

    List<AdcClientCostCenterEntity> persistList( List<ClientCostCenterDTO> clientCostCenterDTO, Long clientId ) throws ValidationException;

    AdcClientCostCenterEntity update( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws
            ValidationException;

    void deleteById( Long id, Long clientId );

    Page<AdcClientCostCenterEntity> getCostCenterPaginated( Long clientId, Long productId, String field, String filter,
                                                            Integer pageNumber, Integer pageSize );

    byte[] getImportTemplateFile( Long clientId );

    AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO, Long clientId );

}
