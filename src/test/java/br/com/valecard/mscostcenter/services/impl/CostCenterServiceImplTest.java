package br.com.valecard.mscostcenter.services.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import static br.com.valecard.mscostcenter.services.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.db.repositories.CostCenterRepository;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;
import br.com.valecard.mscostcenter.services.mapper.ClientCostCenterMapper;

@ExtendWith(MockitoExtension.class)
class CostCenterServiceImplTest {

    @Mock
    private CostCenterRepository costCenterRepository;

    @InjectMocks
    private CostCenterServiceImpl service;

    private ClientCostCenterDTO validDto;
    private AdcClientCostCenterEntity validEntity;

    private static Long CLIENT_ID = 1L;

    @BeforeEach
    void setUp() {
        validDto = new ClientCostCenterDTO();
        validDto.setBranch( 123 );
        validDto.setCode( 123 );
        validDto.setDescription( "description" );
        validDto.setClientId( 1L );
        validDto.setCostCenter( "cost" );
        validDto.setVirtual( "virtual" );
        validDto.setResultCenterId( 2L );

        validEntity = ClientCostCenterMapper.toEntity( validDto );
        validEntity.setId( 1L );
    }

    @Test
    void findById_existingId_returnsEntity() {
        when( costCenterRepository.findById( 1L ) ).thenReturn( Optional.of( validEntity ) );

        AdcClientCostCenterEntity result = service.findById( 1L, CLIENT_ID );

        assertNotNull( result );
        assertEquals( 1L, result.getId() );
    }

    @Test
    void save_validDto_returnsSavedEntity() throws ValidationException {
        when( costCenterRepository.save( any() ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = service.save( validDto, CLIENT_ID );

        assertNotNull( result );
        assertEquals( validDto.getDescription(), result.getDescription() );
    }

    @Test
    void update_existingEntity_updatesAndReturns() throws ValidationException {
        validDto.setId( 1L );
        when( costCenterRepository.findById( 1L ) ).thenReturn( Optional.of( validEntity ) );
        when( costCenterRepository.save( any() ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = service.update( validDto, CLIENT_ID );

        assertNotNull( result );
        verify( costCenterRepository ).save( any() );
    }

    @Test
    void deleteById_existingId_deletesEntity() {
        when( costCenterRepository.findById( 1L ) ).thenReturn( Optional.of( validEntity ) );

        assertDoesNotThrow( () -> service.deleteById( 1L, CLIENT_ID ) );
        verify( costCenterRepository ).delete( validEntity );
    }

    @Test
    void getCostCenterPaginated_withFieldAndFilter_returnsPage() {
        PageRequest pageable = PageRequest.of( 0, 10 );
        List<AdcClientCostCenterEntity> entities = List.of( validEntity );
        Page<AdcClientCostCenterEntity> page = new PageImpl<>( entities );

        when( costCenterRepository.findAll( nullable( Specification.class ), eq( pageable ) ) ).thenReturn(
                page );

        Page<AdcClientCostCenterEntity> result = service.getCostCenterPaginated( CLIENT_ID, null, "code", "co", 0, 10 );

        assertEquals( 1, result.getContent().size() );
        verify( costCenterRepository ).findAll( nullable( Specification.class ), eq( pageable ) );
    }

    @Test
    void save_missingBranch_throwsValidationException() {
        validDto.setBranch( null );
        validDto.setCode( null );
        validDto.setDescription( null );
        ValidationException exception = assertThrows( ValidationException.class,
                () -> service.save( validDto, CLIENT_ID ) );
        assertTrue( exception.getMessage().equals( EXCEPTION_INVALID_DATA ) );
    }

    @Test
    void importData_stub_returnsNull() {
        assertNull( service.importData( validDto, CLIENT_ID ) );
    }

    @Test
    void getImportTemplateFile_always_returnsEmptyArray() {
        assertArrayEquals( new byte[0], service.getImportTemplateFile( 1L ) );
    }
}