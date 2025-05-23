package br.com.valecard.mscrosscostcenter.controllers;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.services.impl.CostCenterService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;

class CostCenterControllerTest {

    @Mock
    private CostCenterService costCenterService;

    @InjectMocks
    private CostCenterController costCenterController;

    private AdcClientCostCenterEntity validEntity;
    private ClientCostCenterDTO validDto;

    private static Long CLIENT_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks( this );

        validEntity = new AdcClientCostCenterEntity();
        validEntity.setId( 1L );

        validDto = new ClientCostCenterDTO();
        validDto.setBranch( 123 );
        validDto.setCode( 123 );
        validDto.setDescription( "description" );
        validDto.setClientId(1L);
    }

    @Test
    void getCostCenter_existingId_returnsEntity() {
        when( costCenterService.findById( 1L ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.getCostCenter( 1L );

        assertNotNull( result );
        assertEquals( 1L, result.getId() );
        verify( costCenterService ).findById( 1L );
    }

    @Test
    void createCostCenter_validDto_returnsSavedEntity() throws ValidationException {
        when( costCenterService.save( validDto ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.createCostCenter( validDto );

        assertNotNull( result );
        assertEquals( validEntity.getId(), result.getId() );
        verify( costCenterService ).save( validDto );
    }

    @Test
    void importCostCenter_validDto_returnsImportedEntity() throws ValidationException {
        when( costCenterService.importData( validDto  ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.importCostCenter( validDto );

        assertNotNull( result );
        assertEquals( validEntity.getId(), result.getId() );
        verify( costCenterService ).importData( validDto );
    }

}