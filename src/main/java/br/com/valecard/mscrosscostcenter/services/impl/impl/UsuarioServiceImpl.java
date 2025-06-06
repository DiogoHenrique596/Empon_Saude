package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.UsuarioPermissaoRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.UsuarioRepository;
import br.com.valecard.mscrosscostcenter.services.impl.UsuarioService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.UsuarioDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ResourceNotFoundException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.dtos.ProblemObject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioPermissaoRepository usuarioPermissaoRepository;

    @Override
    public UsuarioEntity findById(Integer id) {
        Assert.notNull(id, "id não pode ser nulo");
        log.info("Buscando usuário por id: {}", id);
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        log.info("Método de finalização findById com id: {}", id);
        return usuarioEntity;
    }

    @Override
    public List<UsuarioEntity> findAll() throws ValidationException {
        log.info("Buscando todos os usuários");
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity save(UsuarioDTO usuarioDTO) throws ValidationException {
        Assert.notNull(usuarioDTO, "usuarioDTO não pode ser nulo");
        log.info("Salvando usuário: {}", usuarioDTO);

        List<ProblemObject> problemas = validateUsuarioDTO(usuarioDTO);
        if (!problemas.isEmpty()) {
            log.warn("Erros de validação: {}", problemas);
            throw new ValidationException("Dados inválidos", problemas);
        }

        UsuarioEntity entity = toEntity(usuarioDTO);
        entity = usuarioRepository.save(entity);
        log.info("Usuário salvo com id: {}", entity.getId());
        return entity;
    }

    @Override
    public boolean delete(Integer id) throws ValidationException {

        Assert.notNull(id, "id não pode ser nulo");
        log.info("Excluindo usuário com id: {}", id);

        UsuarioEntity entity = usuarioRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        usuarioRepository.delete(entity);
        log.info("Usuário excluído com id: {}", id);
        return true;
    }

    @Override
    public UsuarioEntity update(UsuarioDTO usuarioDTO) throws ValidationException {
        Assert.notNull(usuarioDTO, "usuarioDTO não pode ser nulo");
        Assert.notNull(usuarioDTO.getId(), "id do usuário não pode ser nulo");
        log.info("Atualizando usuário: {}", usuarioDTO);

        UsuarioEntity existente = usuarioRepository.findById(usuarioDTO.getId())
                .orElseThrow(ResourceNotFoundException::new);

        List<ProblemObject> problemas = validateUsuarioDTO(usuarioDTO);
        if (!problemas.isEmpty()) {
            log.warn("Erros de validação: {}", problemas);
            throw new ValidationException("Dados inválidos", problemas);
        }

        // Atualiza apenas os campos do DTO que não são nulos
        if (usuarioDTO.getNome() != null) existente.setNome(usuarioDTO.getNome());
        if (usuarioDTO.getEmail() != null) existente.setEmail(usuarioDTO.getEmail());
        if (usuarioDTO.getSenha() != null) existente.setSenha(usuarioDTO.getSenha());

        UsuarioEntity entity = usuarioRepository.save(existente);
        log.info("Usuário atualizado com id: {}", entity.getId());
        return entity;
    }




    private List<ProblemObject> validateUsuarioDTO(UsuarioDTO usuarioDTO) {
        List<ProblemObject> problemas = new ArrayList<>();
        if (usuarioDTO.getNome() == null || usuarioDTO.getNome().trim().isEmpty()) {
            problemas.add(new ProblemObject("nome", "Nome é obrigatório"));
        }
        return problemas;
    }

    // Conversão manual de DTO para Entity
    private UsuarioEntity toEntity(UsuarioDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        return entity;
    }
}