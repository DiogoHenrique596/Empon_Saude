package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.EnderecoEntity;
import br.com.valecard.mscrosscostcenter.services.impl.EnderecoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.EnderecoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Buscar endereços por ID de pessoa")
    @GetMapping(path = "/{pessoa_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnderecoEntity> findByPessoaId(
            @PathVariable ("pessoa_id")Integer pessoaId) throws ValidationException {
        return enderecoService.findByPessoaId(pessoaId);
    }

    @Operation(summary = "Buscar todos os endereços")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EnderecoEntity> findAll() throws ValidationException {
        return enderecoService.findAll();
    }

    @Operation(summary = "Criar endereço")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody EnderecoEntity createEndereco(
            @RequestBody EnderecoDTO enderecoDTO) throws ValidationException {
        return enderecoService.save(enderecoDTO);
    }

    @Operation(summary = "Atualizar endereço")
    @PutMapping
    public @ResponseBody EnderecoEntity updateEndereco(
            @RequestBody EnderecoDTO enderecoDTO) throws ValidationException {
        return enderecoService.update(enderecoDTO);
    }

    @Operation(summary = "Deletar endereço por ID")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteById(@PathVariable Integer id) throws ValidationException {
        return enderecoService.deleteById(id);
    }

}
