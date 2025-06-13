package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaJuridicaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaJuridicaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaJuridicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/pessoa_juridica")
public class PessoaJuridicaController {

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    @Operation(summary = "Buscar pessoa jurídica por CNPJ")
    @GetMapping(path = "/{cnpj}")
    public @ResponseBody PessoaJuridicaEntity findByCnpj(
            @PathVariable(name = "cnpj")
            @Parameter(description = "cnpj") String cnpj) throws ValidationException {
        return pessoaJuridicaService.findByCnpj(cnpj);
    }

    @Operation(summary = "Buscar todas as pessoas jurídicas")
    @GetMapping
    public @ResponseBody List<PessoaJuridicaEntity> findAll() throws ValidationException {
        return pessoaJuridicaService.findAll();
    }

    @Operation(summary = "Criar pessoa jurídica")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PessoaJuridicaEntity createPessoaJuridica(
            @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException {
        return pessoaJuridicaService.save(pessoaJuridicaDTO);
    }

    @Operation(summary = "Atualizar pessoa jurídica")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody PessoaJuridicaEntity updatePessoaJuridica(
            @RequestBody PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException {;
        return pessoaJuridicaService.update(pessoaJuridicaDTO);
    }

    @Operation(summary = "Deletar pessoa jurídica por CNPJ")
    @DeleteMapping(path = "/{cnpj}")
    public boolean delete(@PathVariable(name = "cnpj")
                              @Parameter(description = "cnpj") String cnpj) throws ValidationException {
        return pessoaJuridicaService.deleteByCnpj(cnpj);
    }
}