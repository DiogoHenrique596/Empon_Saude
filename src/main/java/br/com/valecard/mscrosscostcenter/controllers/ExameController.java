package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.ExameEntity;
import br.com.valecard.mscrosscostcenter.services.impl.ExameService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ExameDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/exames")
public class ExameController {

    @Autowired
    private ExameService exameService;

    @Operation(summary = "Buscar todos os exames")
    @GetMapping
    public List<ExameEntity> findAll() throws ValidationException {
        return exameService.findAll();
    }

    @Operation(summary = "Buscar exame por ID")
    @GetMapping(path = "/{id}")
    public ExameEntity findById(@PathVariable Integer id) throws ValidationException {
        return exameService.findById(id);
    }

    @Operation(summary = "Criar exame")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody ExameEntity createExame(
            @RequestBody ExameDTO exameDTO) throws ValidationException {
        return exameService.save(exameDTO);
    }

    @Operation(summary = "Atualizar exame")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody ExameEntity updateExame(
            @RequestBody ExameDTO exameDto) throws ValidationException {
        return exameService.update(exameDto);
    }

    @DeleteMapping(path = "/{id}")
    public boolean deleteById(@PathVariable Integer id) throws ValidationException {
        exameService.deleteById(id);
        return true;
    }
}
