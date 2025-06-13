package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoId;
import br.com.valecard.mscrosscostcenter.services.impl.UsuarioPermissaoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioPermissaoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/usuariopermissao")
public class UsuarioPermissaoController {

    @Autowired
    private UsuarioPermissaoService usuarioPermissaoService;

    @Operation(summary = "Buscar permissão de usuário por ID")
    @GetMapping(path = "/{usuarioId}")
    public @ResponseBody UsuarioPermissaoEntity getPermissao(
            @PathVariable(name = "usuarioId") @Parameter(description = "ID do usuário") Integer usuarioId,
            @PathVariable(name = "permissaoId") @Parameter(description = "ID da permissão") Integer permissaoId) {
        UsuarioPermissaoId id = new UsuarioPermissaoId(usuarioId, permissaoId);
        return usuarioPermissaoService.findById(id);
    }

    @Operation(summary = "Buscar todas as permissões de usuário")
    @GetMapping
    public @ResponseBody List<UsuarioPermissaoEntity> findAll() throws ValidationException {
        return usuarioPermissaoService.findAll();
    }

    @Operation(summary = "Criar permissão de usuário")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody UsuarioPermissaoEntity createPermissao(
            @RequestBody UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException {
        return usuarioPermissaoService.save(usuarioPermissaoDTO);
    }

    @Operation(summary = "Deletar permissão de usuário por ID")
    @DeleteMapping
    public boolean deletePermissao(
            @RequestParam(name = "usuarioId")@Parameter(description = "usuarioId") Integer usuarioId,
            @RequestParam(name = "permissaoId")@Parameter(description = "permissaoId") Integer permissaoId) throws ValidationException {
        UsuarioPermissaoId id = new UsuarioPermissaoId(usuarioId, permissaoId);
        return usuarioPermissaoService.delete(id);
    }
}