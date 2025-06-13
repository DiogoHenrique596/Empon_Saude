package br.com.valecard.mscrosscostcenter.controllers;

import br.com.valecard.mscrosscostcenter.db.entities.OrcamentoEntity;
import br.com.valecard.mscrosscostcenter.services.impl.OrcamentoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.OrcamentoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rest/empon/orcamento")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @Operation(summary = "Buscar todos os orçamentos ")
    @GetMapping()
    public List<OrcamentoEntity> findAll() throws ValidationException{
        return orcamentoService.findAll();
    }

    @Operation(summary = "Buscar orçamento por ID")
    @GetMapping(path = "/{id}")
    public OrcamentoEntity findById(
            @PathVariable Integer id) throws ValidationException {
        return orcamentoService.findById(id);
    }

    @Operation(summary = "Criar orçamento")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OrcamentoEntity create(
            @RequestBody OrcamentoDTO orcamentoDTO) throws ValidationException {
        return orcamentoService.save(orcamentoDTO);
    }

    @Operation(summary = "Atualizar orçamento")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody OrcamentoEntity update(
            @RequestBody OrcamentoDTO orcamentoDTO) throws ValidationException {
        return orcamentoService.update(orcamentoDTO);
    }

    @Operation(summary = "Deletar orçamento pelo ID")
    @DeleteMapping(path = "/{id}")
    public boolean deleteById(@PathVariable Integer id) throws ValidationException {
        orcamentoService.deleteById(id);
        return true;
    }

}
