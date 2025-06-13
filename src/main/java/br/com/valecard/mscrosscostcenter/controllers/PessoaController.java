package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Operation(summary = "Buscar pessoa por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PessoaEntity> findById(@PathVariable Integer id) {
        PessoaEntity pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Buscar todas as pessoas")
    @GetMapping
    public ResponseEntity<List<PessoaEntity>> findAll() throws ValidationException {
        List<PessoaEntity> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }


    @Operation(summary = "Deletar pessoa por ID")
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) throws ValidationException {
        return  pessoaService.delete(id);
    }
}