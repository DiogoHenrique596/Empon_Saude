package br.com.valecard.mscrosscostcenter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscrosscostcenter.services.impl.CostCenterService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ClientCostCenterDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

@RestController
@RequestMapping("api/rest/cost-center")
public class CostCenterController {

    @Autowired
    private CostCenterService costCenterService;

    @Operation(summary = "Find cost center by Id", description = "[findById] This method should retrieve a client cost center")
    @GetMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity getCostCenter(
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestHeader(name = "clientId", required = true) @Parameter(description = "clientId") Long clientId ) {
        return costCenterService.findById( id, clientId );
    }

    @Operation(summary = "Validate cost center", description = "[validate] This method should validate a client cost center")
    @GetMapping(path = "/validate/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity validateCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @RequestHeader(name = "clientId", required = true) @Parameter(description = "clientId") Long clientId ) throws
            ValidationException {
        return costCenterService.validate( clientCostCenterDTO, clientId );
    }

    @Operation(summary = "Create list cost center by Id", description = "[findById] This method should create a client cost center")
    @PostMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AdcClientCostCenterEntity> listCostCenters(
            @RequestBody List<ClientCostCenterDTO> clientCostCenterDTOs,
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId) throws ValidationException {
        return costCenterService.persistList(clientCostCenterDTOs, clientId);
    }

    @Operation(summary = "Create cost center by Id", description = "[findById] This method should create a client cost center")
    @PostMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity createCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId ) throws
            ValidationException {
        return costCenterService.save( clientCostCenterDTO, clientId );
    }

    @Operation(summary = "Update cost center by Id", description = "[findById] This method should update a client cost center")
    @PutMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity updateCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId ) throws
            ValidationException {
        return costCenterService.update( clientCostCenterDTO, clientId );
    }

    @Operation(summary = "Delete cost center by Id", description = "[findById] This method should delete a client cost center")
    @DeleteMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCostCenter(
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId ) {
        costCenterService.deleteById( id, clientId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @Operation(summary = "Find cost center list by filter", description = "[findById] This method should retrieve a client cost center list")
    @GetMapping(path = "/data-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Page<AdcClientCostCenterEntity> getCostCenterPaginated(
            @RequestParam(name = "clientId", required = true) @Parameter(description = "id") Long clientId,
            @RequestParam(name = "productId", required = false) @Parameter(description = "id") Long productId,
            @RequestParam(name = "field", required = false) @Parameter(description = "field") String field,
            @RequestParam(name = "filter", required = false) @Parameter(description = "filter") String filter,
            @RequestParam(name = "pageNumber", required = true) @Parameter(description = "pageNumber") Integer pageNumber,
            @RequestParam(name = "pageSize", required = true) @Parameter(description = "pageSize") Integer pageSize ) {
        return costCenterService.getCostCenterPaginated( clientId, productId, field, filter, pageNumber, pageSize );
    }

    @Operation(summary = "Import cost center by Id", description = "[findById] This method should create a imported client cost center")
    @PostMapping(path = "/import-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity importCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId ) {
        return costCenterService.importData( clientCostCenterDTO, clientId );
    }

    @Operation(
            summary = "Download cost center file template by filter",
            description = "[downloadImportTemplate] This method retrieves a client cost center template file"
    )
    @GetMapping(path = "/import-file-template", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> downloadCostCenterTemplateFile(
            @RequestHeader(name = "clientId", required = false) @Parameter(description = "clientId") Long clientId ) {

        //TODO definir se vai ficar aqui mesmo
        byte[] fileContent = costCenterService.getImportTemplateFile( clientId );
        String filename = "cost-center-template.xlsx";
        return ResponseEntity
                .ok()
                .contentType( MediaType.APPLICATION_OCTET_STREAM )
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"" )
                .contentLength( fileContent.length )
                .body( new ByteArrayResource( fileContent ) );
    }

}