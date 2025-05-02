package br.com.valecard.mscostcenter.controllers;

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

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.services.CostCenterService;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;

class CostCenterControllerTest {

    @Mock
    private CostCenterService costCenterService;

    @InjectMocks
    private CostCenterController costCenterController;

    private AdcClientCostCenterEntity validEntity;
    private ClientCostCenterDTO validDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks( this );

        validEntity = new AdcClientCostCenterEntity();
        validEntity.setId( 1L );

        validDto = new ClientCostCenterDTO();
        validDto.setBranch( 123 );
        validDto.setCode( 123 );
        validDto.setDescription( "description" );
    }

    @Test
    void getCostCenter_existingId_returnsEntity() {
        when( costCenterService.findById( 1L ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.getCostCenter( 1L, null );

        assertNotNull( result );
        assertEquals( 1L, result.getId() );
        verify( costCenterService ).findById( 1L );
    }

    @Test
    void createCostCenter_validDto_returnsSavedEntity() throws ValidationException {
        when( costCenterService.save( validDto ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.createCostCenter( validDto, null );

        assertNotNull( result );
        assertEquals( validEntity.getId(), result.getId() );
        verify( costCenterService ).save( validDto );
    }

    @Test
    void updateCostCenter_existingEntity_updatesAndReturns() throws ValidationException {
        validDto.setId( 1L );
        when( costCenterService.update( validDto ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.updateCostCenter( validDto, 1L, null );

        assertNotNull( result );
        assertEquals( validEntity.getId(), result.getId() );
        verify( costCenterService ).update( validDto );
    }

    @Test
    void deleteCostCenter_existingId_deletesEntity() {
        doNothing().when( costCenterService ).deleteById( 1L );

        ResponseEntity<String> response = costCenterController.deleteCostCenter( 1L, null );

        assertEquals( 200, response.getStatusCodeValue() );
        verify( costCenterService ).deleteById( 1L );
    }

    @Test
    void downloadCostCenterTemplateFile_always_returnsFile() {
        byte[] fileContent = new byte[]{1, 2, 3};
        when( costCenterService.getImportTemplateFile( 1L, null ) ).thenReturn( fileContent );

        ResponseEntity<?> response = costCenterController.downloadCostCenterTemplateFile( 1L, null );

        assertNotNull( response );
        assertEquals( 200, response.getStatusCodeValue() );
        assertEquals( "application/octet-stream", response.getHeaders().getContentType().toString() );
        verify( costCenterService ).getImportTemplateFile( 1L, null );
    }

    @Test
    void importCostCenter_validDto_returnsImportedEntity() {
        when( costCenterService.importData( validDto, null ) ).thenReturn( validEntity );

        AdcClientCostCenterEntity result = costCenterController.importCostCenter( validDto, null );

        assertNotNull( result );
        assertEquals( validEntity.getId(), result.getId() );
        verify( costCenterService ).importData( validDto, null );
    }

    @Test
    void getCostCenterPaginated_withValidParams_returnsPage() {
        PageRequest pageable = PageRequest.of( 0, 10 );
        List<AdcClientCostCenterEntity> entities = List.of( validEntity );
        Page<AdcClientCostCenterEntity> page = new PageImpl<>( entities );

        when( costCenterService.getCostCenterPaginated( 1L, 2L, "code", "filter", 0, 10, null ) ).thenReturn( page );

        Page<AdcClientCostCenterEntity> result = costCenterController.getCostCenterPaginated( 1L, 2L, "code", "filter",
                0, 10, null );

        assertNotNull( result );
        assertEquals( 1, result.getContent().size() );
        assertEquals( validEntity.getId(), result.getContent().get( 0 ).getId() );
        verify( costCenterService ).getCostCenterPaginated( 1L, 2L, "code", "filter", 0, 10, null );
    }
}