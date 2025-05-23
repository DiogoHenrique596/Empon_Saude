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
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id ) {
        return costCenterService.findById( id );
    }

    @Operation(summary = "Validate cost center", description = "[validate] This method should validate a client cost center")
    @PostMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> validateCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO) throws ValidationException {
         costCenterService.validate( clientCostCenterDTO );
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Create list cost center by Id", description = "[persistListCostCenters] This method should create list a client cost center")
    @PostMapping(path = "/entity/persist-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AdcClientCostCenterEntity> persistListCostCenters(
            @RequestBody List<ClientCostCenterDTO> clientCostCenterDTOs) throws ValidationException {
        return costCenterService.persistList(clientCostCenterDTOs);
    }

    @Operation(summary = "Create cost center by Id", description = "[createCostCenter] This method should create a client cost center")
    @PostMapping(path = "/entity", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity createCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO) throws ValidationException {
        return costCenterService.save( clientCostCenterDTO );
    }

    @Operation(summary = "Import cost center by Id", description = "[importCostCenter] This method should create a imported client cost center")
    @PostMapping(path = "/import-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity importCostCenter(
            @RequestBody ClientCostCenterDTO clientCostCenterDTO ) throws ValidationException {
        return costCenterService.importData( clientCostCenterDTO );
    }

    @Operation(summary = "Import cost center by Id seed Data", description = "[getCostCenterSeedData] This method should seed data client cost center")
    @GetMapping(path = "/seed-data/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AdcClientCostCenterEntity getCostCenterSeedData(
            @PathVariable(name = "id", required = true) @Parameter(description = "id") Long id ) throws ValidationException {
        return costCenterService.findByClientIdSeedData( id );
    }

}