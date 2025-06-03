package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import br.com.valecard.mscrosscostcenter.services.impl.UsuarioService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UsuarioEntity getUsuario(
            @PathVariable(name = "id") @Parameter(description = "id") Integer id) {
        return usuarioService.findById(id);
    }

    @Operation(summary = "Buscar todos os usuários")
    @GetMapping
    public @ResponseBody List<UsuarioEntity> getAllUsuarios(
            @RequestBody(required = false) UsuarioDTO usuarioDTO) throws ValidationException {
        return usuarioService.findAll(usuarioDTO);
    }

    @Operation(summary = "Criar usuário")
    @PostMapping
    public @ResponseBody UsuarioEntity createUsuario(
            @RequestBody UsuarioDTO usuarioDTO) throws ValidationException {
        return usuarioService.save(usuarioDTO);
    }

    @Operation(summary = "Atualizar usuário")
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UsuarioEntity updateUsuario(
            @RequestBody UsuarioDTO usuarioDTO) throws ValidationException {
        return usuarioService.update(usuarioDTO);
    }

    @Operation(summary = "Deletar usuário por ID")
    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UsuarioEntity deleteUsuario(
            @PathVariable(name = "id") @Parameter(description = "id") Integer id) throws ValidationException {
        return usuarioService.delete(id);
    }

}