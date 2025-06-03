package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEntity> findById(@PathVariable Integer id) {
        PessoaEntity pessoa = pessoaService.findById(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<PessoaEntity>> findAll(PessoaDTO pessoaDTO) throws ValidationException {
        List<PessoaEntity> pessoas = pessoaService.findAll(pessoaDTO);
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping
    public ResponseEntity<PessoaEntity> save(@RequestBody PessoaDTO pessoaDTO) throws ValidationException {
        PessoaEntity pessoa = pessoaService.save(pessoaDTO);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaEntity> update(@PathVariable Integer id, @RequestBody PessoaDTO pessoaDTO) throws ValidationException {
        pessoaDTO.setId(id);
        PessoaEntity pessoa = pessoaService.update(pessoaDTO);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaEntity> delete(@PathVariable Integer id) throws ValidationException {
        PessoaEntity pessoa = pessoaService.delete(id);
        return ResponseEntity.ok(pessoa);
    }
}