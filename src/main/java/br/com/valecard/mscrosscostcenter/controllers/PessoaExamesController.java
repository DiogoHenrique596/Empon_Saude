package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesId;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaExamesService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaExamesDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/pessoa-exames")
public class PessoaExamesController {

        @Autowired
        private PessoaExamesService pessoaExamesService;


        @Operation(summary = "Buscar todos os exames ")
        @GetMapping()
        public List<PessoaExamesEntity> findAll() throws ValidationException {
            return pessoaExamesService.findAll();
        }


        @Operation(summary = "Buscar todos os exames de uma pessoa física pelo ID")
        @GetMapping(path = "/{pessoaFisicaId}")
        public List<PessoaExamesEntity> findByPessoaFisica(@PathVariable Integer pessoaFisicaId) throws ValidationException {
            return pessoaExamesService.findByPessoaFisicaId(pessoaFisicaId);
        }

        @Operation(summary = "Criar exame de pessoa")
        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody PessoaExamesEntity create(
                @RequestBody PessoaExamesDTO pessoaExamesDTO) throws ValidationException {
            return pessoaExamesService.save(pessoaExamesDTO);
        }


    @Operation(summary = "Deletar exame de pessoa pelo DTO")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteByDto(@RequestBody PessoaExamesDTO pessoaExamesDTO) throws ValidationException {
        pessoaExamesService.deleteByDto(pessoaExamesDTO);
        return true;
    }

}
