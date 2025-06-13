package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaFisicaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaFisicaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaFisicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/pessoa_fisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Operation(summary = "Buscar pessoa fisica por CPF")
    @GetMapping(path = "/{cpf}")
   public PessoaFisicaEntity findByCpf(
            @PathVariable(name = "cpf") @Parameter(description = "cpf") String cpf) throws ValidationException {
        return pessoaFisicaService.findByCpf(cpf);
    }

    @Operation(summary = "Buscar todas as pessoas fisicas")
    @GetMapping
    public @ResponseBody List<PessoaFisicaEntity> findAll() throws ValidationException {
        return pessoaFisicaService.findAll();
    }

    @Operation(summary = "Criar pessoa fisica")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody PessoaFisicaEntity createPessoaFisica(
            @RequestBody PessoaFisicaDTO pessoaFisicaDto) throws ValidationException {
        return pessoaFisicaService.save(pessoaFisicaDto);
    }

    @Operation(summary = "Atualizar pessoa fisica")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE )
    public @ResponseBody PessoaFisicaEntity updatePessoaFisica(
            @RequestBody PessoaFisicaDTO pessoaFisicaDto) throws ValidationException {
        return pessoaFisicaService.update(pessoaFisicaDto);
    }

    @Operation(summary = "Deletar pessoa fisica por CPF")
    @DeleteMapping(path = "/{cpf}")
    public boolean delete(@PathVariable(name = "cpf")
                              @Parameter(description = "cpf") String cpf) throws ValidationException {
        return pessoaFisicaService.deleteByCpf(cpf);
    }
}
