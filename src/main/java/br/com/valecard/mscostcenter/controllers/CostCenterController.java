package br.com.valecard.mscostcenter.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;
import br.com.valecard.mscostcenter.services.CostCenterService;
import br.com.valecard.mscostcenter.services.dtos.ClientCostCenterDTO;
import br.com.valecard.mscostcenter.services.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("api/rest/cost-center")
public class CostCenterController {

    @Autowired
    private CostCenterService costCenterService;

    @Operation(summary = "Find cost center by Id", description = "[findById] This method should retrieve a client cost center")
    @GetMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity getCostCenter(
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) {
        return costCenterService.findById( id );
    }

    @Operation(summary = "Create cost center by Id", description = "[findById] This method should create a client cost center")
    @PostMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity createCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) throws
            ValidationException {
        return costCenterService.save( clientCostCenterDTO );
    }

    @Operation(summary = "Update cost center by Id", description = "[findById] This method should update a client cost center")
    @PutMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity updateCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) throws
            ValidationException {
        return costCenterService.update( clientCostCenterDTO );
    }

    @Operation(summary = "Delete cost center by Id", description = "[findById] This method should delete a client cost center")
    @DeleteMapping(path = "/entity/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCostCenter(
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) {
        costCenterService.deleteById( id );
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
            @RequestParam(name = "pageSize", required = true) @Parameter(description = "pageSize") Integer pageSize,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) {
        return costCenterService.getCostCenterPaginated( clientId, productId, field, filter, pageNumber, pageSize,
                thirdPartyUserId );
    }

    @Operation(summary = "Import cost center by Id", description = "[findById] This method should create a imported client cost center")
    @PostMapping(path = "/import-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity importCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) {
        return costCenterService.importData( clientCostCenterDTO, thirdPartyUserId );
    }

    @Operation(
            summary = "Download cost center file template by filter",
            description = "[downloadImportTemplate] This method retrieves a client cost center template file"
    )
    @GetMapping(path = "/import-file-template", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<ByteArrayResource> downloadCostCenterTemplateFile(
            @RequestParam(name = "clientId", required = true) @Parameter(description = "clientId") Long clientId,
            @RequestParam(name = "thirdPartyUserId", required = false) @Parameter(description = "thirdPartyUserId") Long thirdPartyUserId ) {

        //TODO definir se vai ficar aqui mesmo
        byte[] fileContent = costCenterService.getImportTemplateFile( clientId, thirdPartyUserId );
        String filename = "cost-center-template.xlsx";
        return ResponseEntity
                .ok()
                .contentType( MediaType.APPLICATION_OCTET_STREAM )
                .header( HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"" )
                .contentLength( fileContent.length )
                .body( new ByteArrayResource( fileContent ) );
    }

}