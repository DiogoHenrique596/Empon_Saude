package br.com.valecard.mscrosscostcenter.services.impl.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static br.com.valecard.mscrosscostcenter.services.impl.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;
import static br.com.valecard.mscrosscostcenter.services.impl.exceptions.message.InfrastructureMessageId.EXCEPTION_REQUIRED_NOT_FOUND;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.CostCenterRepository;
import br.com.valecard.mscrosscostcenter.services.impl.CostCenterService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ResourceNotFoundException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.dtos.ProblemObject;
import br.com.valecard.mscrosscostcenter.services.impl.mapper.ClientCostCenterMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CostCenterServiceImpl implements CostCenterService {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Override
    public AdcClientCostCenterEntity findById( Long id ) {

        Assert.notNull( id, "id cannot be null" );

        log.info( "Starting method findById with parameters - id: {}, clientId: {}", id );
        AdcClientCostCenterEntity adcClientCostCenter = costCenterRepository.findById( id ).orElseThrow(
                ResourceNotFoundException::new );
        log.info( "Finishing method findById with id: {}", id );
        return adcClientCostCenter;
    }

    @Override
    public AdcClientCostCenterEntity validate(ClientCostCenterDTO clientCostCenterDTO) throws ValidationException {
        Assert.notNull(clientCostCenterDTO, "clientCostCenterDTO cannot be null");

        log.info("Starting method validate. costCenterDTO: {}",  clientCostCenterDTO);

        List<ProblemObject> validationErrors = validateClientCostCenterDTO(clientCostCenterDTO);
        if (!validationErrors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation errors: ");
            for (ProblemObject error : validationErrors) {
                errorMessage.append(error.toString()).append("; ");
            }
            log.warn(errorMessage.toString());
            throw new ValidationException("Invalid data", validationErrors);
        }

        log.debug("Mapping ClientCostCenterDTO to AdcClientCostCenterEntity. DTO: {}", clientCostCenterDTO);
        AdcClientCostCenterEntity entity = ClientCostCenterMapper.toEntity(clientCostCenterDTO);
        entity.setClientId(clientCostCenterDTO.getClientId());

        log.info("Successfully validated entity. Entity ID: {}", entity.getId());
        return entity;
    }

    @Override
    public List<AdcClientCostCenterEntity> persistList(List<ClientCostCenterDTO> listClientCostCenterDTO) throws ValidationException {
        if (listClientCostCenterDTO == null || listClientCostCenterDTO.isEmpty()) {
            throw new ValidationException("List of cost centers cannot be null or empty");
        }

        List<AdcClientCostCenterEntity> listPersistedEntities = new ArrayList<>();
        List<ProblemObject> allValidationErrors = new ArrayList<>();

        for (ClientCostCenterDTO clientCostCenterDTO : listClientCostCenterDTO) {
            Assert.notNull(clientCostCenterDTO, "clientCostCenterDTO cannot be null");
            log.info("Starting method save.  costCenterDTO: {}",  clientCostCenterDTO);

            List<ProblemObject> validationErrors = validateClientCostCenterDTO(clientCostCenterDTO);
            if (!validationErrors.isEmpty()) {
                allValidationErrors.addAll(validationErrors);
            } else {
                log.debug("Mapping ClientCostCenterDTO to AdcClientCostCenterEntity. DTO: {}", clientCostCenterDTO);
                AdcClientCostCenterEntity entity = ClientCostCenterMapper.toEntity(clientCostCenterDTO);
                entity.setClientId(clientCostCenterDTO.getClientId());

                log.debug("Saving entity to repository. Entity: {}", entity);
                entity = costCenterRepository.save(entity);

                log.info("Successfully saved entity. Entity ID: {}", entity.getId());
                listPersistedEntities.add(entity);
            }
        }

        if (!allValidationErrors.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation errors: ");
            for (ProblemObject error : allValidationErrors) {
                errorMessage.append(error.toString()).append("; ");
            }
            log.warn(errorMessage.toString());
            throw new ValidationException("Invalid data", allValidationErrors);
        }

        log.info("Successfully saved list of entities. Entity Count: {}", listPersistedEntities.size());
        return listPersistedEntities;
    }

    @Override
    public AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO ) throws
            ValidationException {
        Assert.notNull( clientCostCenterDTO, "clientCostCenterDTO cannot be null" );

        log.info( "Starting method save.  costCenterDTO: {}",
                 clientCostCenterDTO );

        validateClientCostCenterDTO( clientCostCenterDTO );

        log.debug( "Mapping ClientCostCenterDTO to AdcClientCostCenterEntity. DTO: {}", clientCostCenterDTO );
        AdcClientCostCenterEntity entity = ClientCostCenterMapper.toEntity( clientCostCenterDTO );

        log.debug( "Saving entity to repository. Entity: {}", entity );
        entity = costCenterRepository.save( entity );

        log.info( "Successfully saved entity. Entity ID: {}", entity.getId() );
        return entity;
    }


    @Override
    public AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO ) {
        log.info( "Starting method importData" );
        return null;
    }

    private List<ProblemObject> validateClientCostCenterDTO( ClientCostCenterDTO clientCostCenterDTO ) throws
            ValidationException {

        log.info( "Starting validation for ClientCostCenterDTO: {}", clientCostCenterDTO );
        List<ProblemObject> problems = new ArrayList<>();

        if ( clientCostCenterDTO.getBranch() == null ) {
            problems.add( new ProblemObject( "branch", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Filial" ) ) );
        }
        if ( clientCostCenterDTO.getCode() == null ) {
            problems.add( new ProblemObject( "code", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Código" ) ) );
        }
        if ( StringUtils.isBlank( clientCostCenterDTO.getDescription() ) ) {
            problems.add(
                    new ProblemObject( "description", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Descrição" ) ) );
        }
/*
        if ( !problems.isEmpty() ) {
            log.warn( "Validation errors found: {}", problems );
            throw new ValidationException( EXCEPTION_INVALID_DATA, problems );
        }
*/
        log.info( "Validation successful for ClientCostCenterDTO: {}", clientCostCenterDTO );
        return problems;
    }

}
