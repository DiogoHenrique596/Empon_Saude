package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.ExameEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.ExameRepository;
import br.com.valecard.mscrosscostcenter.services.impl.ExameService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.ExameDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameServiceImpl implements ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Override
    public ExameEntity findById(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID não pode ser nulo");
        }
        return exameRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Exame não encontrado para ID: " + id));
    }

    @Override
    public List<ExameEntity> findAll() throws ValidationException {
        List<ExameEntity> exames = exameRepository.findAll();
        if (exames.isEmpty()) {
            throw new ValidationException("Nenhum exame encontrado");
        }
        return exames;
    }

    @Override
    public ExameEntity save(ExameDTO exameDTO) throws ValidationException {
        if (exameDTO == null) {
            throw new ValidationException("Exame não pode ser nulo");
        }
        if (exameDTO.getCodigo() == null || exameDTO.getCodigo().isEmpty()) {
            throw new ValidationException("Código do exame não pode ser nulo ou vazio");
        }

        ExameEntity entity = toEntity(exameDTO);
        return exameRepository.save(entity);
    }

    @Override
    public ExameEntity update(ExameDTO exameDTO) throws ValidationException {
        if (exameDTO == null || exameDTO.getId() == null) {
            throw new ValidationException("Exame não pode ser nulo e deve ter um ID válido");
        }
        ExameEntity entity = exameRepository.findById(exameDTO.getId())
                .orElseThrow(() -> new ValidationException("Exame não encontrado para ID: " + exameDTO.getId()));

        entity.setCodigo(exameDTO.getCodigo());
        entity.setDescricao(exameDTO.getDescricao());
        entity.setTipo(exameDTO.getTipo());

        return exameRepository.save(entity);
    }

    @Override
    public boolean deleteById(Integer id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID não pode ser nulo");
        }
        ExameEntity exameEntity = exameRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Exame não encontrado para ID: " + id));

        exameRepository.delete(exameEntity);
        return true;
    }

    private ExameEntity toEntity(ExameDTO exameDTO) {
        ExameEntity entity = new ExameEntity();

        entity.setCodigo(exameDTO.getCodigo());
        entity.setDescricao(exameDTO.getDescricao());
        entity.setTipo(exameDTO.getTipo());

        return entity;
    }
}
