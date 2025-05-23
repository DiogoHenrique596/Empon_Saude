package br.com.valecard.mscrosscostcenter.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.valecard.mscrosscostcenter.services.impl.impl.CostCenterServiceImpl;
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

import static br.com.valecard.mscrosscostcenter.services.impl.exceptions.message.InfrastructureMessageId.EXCEPTION_INVALID_DATA;
import static org.junit.Assert.assertFalse;
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
import static org.mockito.Mockito.*;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.CostCenterRepository;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import br.com.valecard.mscrosscostcenter.services.impl.mapper.ClientCostCenterMapper;

@ExtendWith(MockitoExtension.class)
class CostCenterServiceImplTest {

    @Mock
    private CostCenterRepository costCenterRepository;

    @InjectMocks
    private CostCenterServiceImpl service;

    private ClientCostCenterDTO validDto;
    private ClientCostCenterDTO invalidDto;
    private AdcClientCostCenterEntity validEntity;


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

        AdcClientCostCenterEntity result = service.findById( 1L );

        assertNotNull( result );
        assertEquals( 1L, result.getId() );
    }

    @Test
    void save_validDto_returnsSavedEntity() throws ValidationException {
        when( costCenterRepository.save( any() ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = service.save( validDto );

        assertNotNull( result );
        assertEquals( validDto.getDescription(), result.getDescription() );
    }

    @Test
    void save_nullBranch_throwsValidationException() {
        validDto.setBranch(null);
        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.save(validDto));
        assertTrue(exception.getMessage().contains("inválidos"));
    }

       @Test
    void save_blankDescription_throwsValidationException() {
        validDto.setDescription("   ");
        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.save(validDto));
        assertEquals(EXCEPTION_INVALID_DATA, exception.getMessage());
    }

    @Test
    void save_blankCostCenter_throwsValidationException() {
        validDto.setCostCenter(" ");
        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.save(validDto));
        assertEquals(EXCEPTION_INVALID_DATA, exception.getMessage());
    }
    @Test
    void persistList_validDTOs_savesAndReturnsList() throws ValidationException {
        List<ClientCostCenterDTO> dtos = Arrays.asList(validDto, validDto);
        when(costCenterRepository.save(any())).thenReturn(validEntity);

        List<AdcClientCostCenterEntity> result = service.persistList(dtos);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(costCenterRepository, times(2)).save(any());
    }

    @Test
    void persistList_emptyList_throwsValidationException() {
        List<ClientCostCenterDTO> dtos = new ArrayList<>();

        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.persistList(dtos));

        assertEquals("List of cost centers cannot be null or empty", exception.getMessage());
        verify(costCenterRepository, never()).save(any());
    }

    @Test
    void persistList_nullList_throwsValidationException() {
        ValidationException exception = assertThrows(ValidationException.class,
                () -> service.persistList(null));

        assertEquals("List of cost centers cannot be null or empty", exception.getMessage());
        verify(costCenterRepository, never()).save(any());
    }

    @Test
    void validate_validDTO_returnsEntity() throws ValidationException {
        AdcClientCostCenterEntity result = service.validate(validDto);

        assertNotNull(result);
        assertEquals(validDto.getDescription(), result.getDescription());
    }


    @Test
    void validate_nullDTO_throwsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> service.validate(null));

        assertEquals("clientCostCenterDTO cannot be null", exception.getMessage());
    }
}