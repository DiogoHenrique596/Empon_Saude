package br.com.valecard.mscostcenter.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static br.com.valecard.mscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;
import static br.com.valecard.mscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_REQUIRED_NOT_FOUND;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.db.repositories.CostCenterRepository;
import br.com.valecard.mscostcenter.services.CostCenterService;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;
import br.com.valecard.mscostcenter.services.exceptions.dtos.ProblemObject;
import br.com.valecard.mscostcenter.services.mapper.ClientCostCenterMapper;
import jakarta.persistence.criteria.Predicate;

@Service
public class CostCenterServiceImpl implements CostCenterService {
    Logger LOG = LogManager.getLogger( CostCenterServiceImpl.class );

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Override
    public AdcClientCostCenterEntity findById( Long id ) {

        Assert.notNull( id, "id cannot be null" );
        LOG.info( "Starting method findById" );

        AdcClientCostCenterEntity adcClientCostCenter = costCenterRepository.findById( id ).orElse( null );
        LOG.info( "Finishing method findById" );
        return adcClientCostCenter;
    }

    @Override
    public AdcClientCostCenterEntity save( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException {
        Assert.notNull( clientCostCenterDTO, "clientCostCenterDTO cannot be null" );
        LOG.info( "Starting method save" );

        validateClientCostCenterDTO( clientCostCenterDTO );

        AdcClientCostCenterEntity entity = ClientCostCenterMapper.toEntity( clientCostCenterDTO );
        entity = costCenterRepository.save( entity );
        LOG.info( "Finishing method save" );
        return entity;
    }

    @Override
    public AdcClientCostCenterEntity update( ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException {
        Assert.notNull( clientCostCenterDTO, "clientCostCenterDTO cannot be null" );
        Assert.notNull( clientCostCenterDTO.getId(), "id cannot be null" );

        validateClientCostCenterDTO( clientCostCenterDTO );

        LOG.info( "Starting method update" );
        AdcClientCostCenterEntity entity = costCenterRepository.findById( clientCostCenterDTO.getId() ).orElseThrow(
                () -> new IllegalArgumentException( "Entity not found" ) );

        //Recover updatable fields
        entity.setBranch( clientCostCenterDTO.getBranch() );
        entity.setCode( clientCostCenterDTO.getCode() );
        entity.setDescription( clientCostCenterDTO.getDescription() );
        entity.setCostCenter( clientCostCenterDTO.getCostCenter() );
        entity.setClientId( clientCostCenterDTO.getClientId() );
        entity.setResultCenterId( clientCostCenterDTO.getResultCenterId() );
        entity.setVirtual( clientCostCenterDTO.getVirtual() );
        return costCenterRepository.save( entity );
    }

    @Override
    public void deleteById( Long id ) {

        Assert.notNull( id, "id cannot be null" );
        LOG.info( "Starting method deleteById: " + id );

        AdcClientCostCenterEntity entity = costCenterRepository.findById( id ).orElseThrow(
                () -> new IllegalArgumentException( "Entity not found" ) );
        costCenterRepository.delete( entity );
        LOG.info( "Finishing method deleteById: " + id );
    }

    @Override
    public Page<AdcClientCostCenterEntity> getCostCenterPaginated( Long clientId, Long productId, String field,
                                                                   String filter,
                                                                   Integer pageNumber, Integer pageSize,
                                                                   Long thirdPartyUserId ) {
        LOG.info( "Starting method getCostCenterPaginated" );

        Assert.notNull( clientId, "clientId cannot be null" );

        Pageable pageable = PageRequest.of( pageNumber, pageSize );

        Specification<AdcClientCostCenterEntity> specification = ( root, query, criteriaBuilder ) -> {
            Predicate predicate = criteriaBuilder.equal( root.get( "clientId" ), clientId );

            if ( field != null && filter != null ) {
                predicate = criteriaBuilder.and( predicate,
                        criteriaBuilder.like( root.get( field ), "%" + filter + "%" ) );
            }

            return predicate;
        };

        return costCenterRepository.findAll( specification, pageable );
    }

    @Override
    public byte[] getImportTemplateFile( Long clientId, Long thirdPartyUserId ) {
        LOG.info( "Starting method getImportTemplateFile" );
        return new byte[0];
    }

    @Override
    public AdcClientCostCenterEntity importData( ClientCostCenterDTO clientCostCenterDTO, Long thirdPartyUserId ) {
        LOG.info( "Starting method importData" );
        return null;
    }

    private List<ProblemObject> validateClientCostCenterDTO( ClientCostCenterDTO clientCostCenterDTO ) throws
            ValidationException {
        List<ProblemObject> problems = new ArrayList<>();

        if ( clientCostCenterDTO.getBranch() == null ) {
            problems.add( new ProblemObject( "branch", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Branch" ) ) );
        }
        if ( clientCostCenterDTO.getCode() == null ) {
            problems.add( new ProblemObject( "code", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Code" ) ) );
        }
        if ( StringUtils.isBlank( clientCostCenterDTO.getDescription() ) ) {
            problems.add(
                    new ProblemObject( "description", String.format( EXCEPTION_REQUIRED_NOT_FOUND, "Description" ) ) );
        }

        if ( !problems.isEmpty() ) {
            LOG.error( "Validation errors: " + problems );
            throw new ValidationException( EXCEPTION_INVALID_DATA, problems );
        }

        return problems;
    }

}
