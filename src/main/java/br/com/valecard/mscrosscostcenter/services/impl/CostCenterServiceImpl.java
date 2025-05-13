package br.com.valecard.mscrosscostcenter.services.impl;

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

import static br.com.valecard.mscrosscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;
import static br.com.valecard.mscrosscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_REQUIRED_NOT_FOUND;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.CostCenterRepository;
import br.com.valecard.mscrosscostcenter.services.CostCenterService;
import br.com.valecard.mscrosscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.exceptions.ResourceNotFoundException;
import br.com.valecard.mscrosscostcenter.services.exceptions.ValidationException;
import br.com.valecard.mscrosscostcenter.services.exceptions.dtos.ProblemObject;
import br.com.valecard.mscrosscostcenter.services.mapper.ClientCostCenterMapper;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CostCenterServiceImpl implements CostCenterService {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Override
    public AdcClientCostCenterEntity findById( Long id, Long clientId ) {

        Assert.notNull( id, "id cannot be null" );
        Assert.notNull( clientId, "clientId cannot be null" );

        log.info( "Starting method findById with parameters - id: {}, clientId: {}", id, clientId );
        AdcClientCostCenterEntity adcClientCostCenter = costCenterRepository.findById( id ).orElseThrow(
                ResourceNotFoundException::new );
        log.info( "Finishing method findById with id: {}", id );
        return adcClientCostCenter;
    }

    @Override
    public AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws
            ValidationException {
        Assert.notNull( clientCostCenterDTO, "clientCostCenterDTO cannot be null" );

        log.info( "Starting method save. clientId: {}, costCenterDTO: {}",
                clientId, clientCostCenterDTO );

        validateClientCostCenterDTO( clientCostCenterDTO );

        log.debug( "Mapping ClientCostCenterDTO to AdcClientCostCenterEntity. DTO: {}", clientCostCenterDTO );
        AdcClientCostCenterEntity entity = ClientCostCenterMapper.toEntity( clientCostCenterDTO );

        log.debug( "Saving entity to repository. Entity: {}", entity );
        entity = costCenterRepository.save( entity );

        log.info( "Successfully saved entity. Entity ID: {}", entity.getId() );
        return entity;
    }

    @Override
    public AdcClientCostCenterEntity update( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) throws
            ValidationException {
        log.info( "Starting method update for clientCostCenterDTO: {}", clientCostCenterDTO );

        Assert.notNull( clientCostCenterDTO, "clientCostCenterDTO cannot be null" );
        Assert.notNull( clientCostCenterDTO.getId(), "id cannot be null" );

        log.debug( "Fetching entity with ID: {}", clientCostCenterDTO.getId() );
        AdcClientCostCenterEntity entity = costCenterRepository.findById( clientCostCenterDTO.getId() ).orElseThrow(
                () -> new ResourceNotFoundException() );

        validateClientCostCenterDTO( clientCostCenterDTO );

        log.debug( "Updating fields for entity with ID: {}", clientCostCenterDTO.getId() );

        entity = ClientCostCenterMapper.toEntity( clientCostCenterDTO );

        log.debug( "Saving updated entity with ID: {}", clientCostCenterDTO.getId() );
        AdcClientCostCenterEntity updatedEntity = costCenterRepository.save( entity );

        log.info( "Successfully updated entity with ID: {}", updatedEntity.getId() );
        return updatedEntity;
    }

    @Override
    public void deleteById( Long id, Long clientId ) {

        Assert.notNull( id, "id cannot be null" );
        Assert.notNull( clientId, "clientId cannot be null" );

        log.info( "Starting method deleteById. id: {}, clientId: {}", id, clientId );

        log.debug( "Fetching entity with ID: {}", id );
        AdcClientCostCenterEntity entity = costCenterRepository.findById( id ).orElseThrow(
                () -> new ResourceNotFoundException() );

        log.debug( "Deleting entity with ID: {}", id );
        costCenterRepository.delete( entity );

        log.info( "Finishing method deleteById. id: {}", id );
    }

    @Override
    public Page<AdcClientCostCenterEntity> getCostCenterPaginated( Long clientId, Long productId, String field,
                                                                   String filter, Integer pageNumber,
                                                                   Integer pageSize ) {

        log.info(
                "Starting method getCostCenterPaginated with parameters - clientId: {}, productId: {}, field: {}, filter: {}, pageNumber: {}, pageSize: {}",
                clientId, productId, field, filter, pageNumber, pageSize );

        Assert.notNull( clientId, "clientId cannot be null" );

        log.debug( "Creating pageable object with pageNumber: {} and pageSize: {}", pageNumber, pageSize );
        Pageable pageable = PageRequest.of( pageNumber, pageSize );

        log.debug( "Building specification for query" );
        Specification<AdcClientCostCenterEntity> specification = ( root, query, criteriaBuilder ) -> {
            Predicate predicate = criteriaBuilder.equal( root.get( "clientId" ), clientId );
            log.trace( "Initial predicate: clientId = {}", clientId );

            if ( field != null && filter != null ) {
                log.debug( "Adding filter to specification - field: {}, filter: {}", field, filter );
                predicate = criteriaBuilder.and( predicate,
                        criteriaBuilder.like( root.get( field ), "%" + filter + "%" ) );
            }

            return predicate;
        };

        log.debug( "Executing repository query with specification and pageable" );
        Page<AdcClientCostCenterEntity> result = costCenterRepository.findAll( specification, pageable );

        log.info( "Finished method getCostCenterPaginated. Total elements found: {}", result.getTotalElements() );
        return result;
    }

    @Override
    public byte[] getImportTemplateFile( Long clientId ) {
        log.info( "Starting method getImportTemplateFile" );
        return new byte[0];
    }

    @Override
    public AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO, Long clientId ) {
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

        if ( !problems.isEmpty() ) {
            log.warn( "Validation errors found: {}", problems );
            throw new ValidationException( EXCEPTION_INVALID_DATA, problems );
        }

        log.info( "Validation successful for ClientCostCenterDTO: {}", clientCostCenterDTO );
        return problems;
    }

}
