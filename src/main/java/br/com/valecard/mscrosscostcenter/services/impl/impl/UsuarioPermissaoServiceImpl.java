package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoId;
import br.com.valecard.mscrosscostcenter.db.repositories.UsuarioPermissaoRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.UsuarioRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.PermissaoRepository;
import br.com.valecard.mscrosscostcenter.services.impl.UsuarioPermissaoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioPermissaoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ResourceNotFoundException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.dtos.ProblemObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsuarioPermissaoServiceImpl implements UsuarioPermissaoService {

    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public UsuarioPermissaoEntity findById(UsuarioPermissaoId id) {
        Assert.notNull(id, "id não pode ser nulo");
        log.info("Buscando UsuarioPermissao por id: {}", id);
        return usuarioPermissaoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<UsuarioPermissaoEntity> findAll() throws ValidationException {
        log.info("Buscando todos os UsuarioPermissao");
        return usuarioPermissaoRepository.findAll();
    }

    @Override
    public UsuarioPermissaoEntity save(UsuarioPermissaoDTO usuarioPermissaoDTO) throws ValidationException {
        Assert.notNull(usuarioPermissaoDTO, "usuarioPermissaoDTO não pode ser nulo");
        log.info("Salvando UsuarioPermissao: {}", usuarioPermissaoDTO);

        List<ProblemObject> problemas = validateUsuarioPermissaoDTO(usuarioPermissaoDTO);
        if (!problemas.isEmpty()) {
            log.warn("Erros de validação: {}", problemas);
            throw new ValidationException("Dados inválidos", problemas);
        }

        UsuarioPermissaoEntity entity = toEntity(usuarioPermissaoDTO);
        entity = usuarioPermissaoRepository.save(entity);
        log.info("UsuarioPermissao salvo: {}", entity);
        return entity;
    }

    @Override
    public boolean delete(UsuarioPermissaoId id) throws ValidationException {

        Assert.notNull(id, "id não pode ser nulo");
        log.info("Excluindo UsuarioPermissao com id: {}", id);
        UsuarioPermissaoEntity entity = usuarioPermissaoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        usuarioPermissaoRepository.delete(entity);
        log.info("UsuarioPermissao excluído com id: {}", id);
        return true;
    }


    private List<ProblemObject> validateUsuarioPermissaoDTO(UsuarioPermissaoDTO dto) {
        List<ProblemObject> problemas = new ArrayList<>();
        if (dto.getUsuarioId() == null) {
            problemas.add(new ProblemObject("usuarioId", "Usuário é obrigatório"));
        }
        if (dto.getPermissaoId() == null) {
            problemas.add(new ProblemObject("permissaoId", "Permissão é obrigatória"));
        }
        return problemas;
    }

    private UsuarioPermissaoEntity toEntity(UsuarioPermissaoDTO dto) {
        UsuarioPermissaoEntity entity = new UsuarioPermissaoEntity();
        entity.setId(new UsuarioPermissaoId(dto.getUsuarioId(), dto.getPermissaoId()));
        entity.setUsuario(usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(ResourceNotFoundException::new));
        entity.setPermissao(permissaoRepository.findById(dto.getPermissaoId())
                .orElseThrow(ResourceNotFoundException::new));
        return entity;
    }
}