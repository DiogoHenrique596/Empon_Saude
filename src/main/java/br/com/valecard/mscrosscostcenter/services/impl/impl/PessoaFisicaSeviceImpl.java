package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaFisicaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaFisicaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaFisicaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaFisicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaSeviceImpl implements PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;


    @Override
    public PessoaFisicaEntity findByCpf(String cpf) throws ValidationException {
        if (cpf == null || cpf.isEmpty()) {
            throw new ValidationException("CPF não pode ser nulo ou vazio");
        }
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.findByCpf(cpf);
        if (pessoaFisicaEntity == null) {
            throw new ValidationException("Pessoa Física não encontrada para CPF: " + cpf);
        }
        return pessoaFisicaEntity;
    }

    @Override
    public List<PessoaFisicaEntity> findAll() throws ValidationException {
        List<PessoaFisicaEntity> pessoasFisicas = pessoaFisicaRepository.findAll();
        if (pessoasFisicas.isEmpty()) {
            throw new ValidationException("Nenhuma Pessoa Física encontrada");
        }
        return pessoasFisicas;
    }

    @Override
    public PessoaFisicaEntity save(PessoaFisicaDTO pessoaFisicaDTO) throws ValidationException {
        if (pessoaFisicaDTO == null) {
            throw new ValidationException("Pessoa Física não pode ser nula");
        }
        if (pessoaFisicaDTO.getCpf() == null || pessoaFisicaDTO.getCpf().isEmpty()) {
            throw new ValidationException("CPF não pode ser nulo ou vazio");
        }
        if (pessoaFisicaRepository.findByCpf(pessoaFisicaDTO.getCpf()) != null) {
            throw new ValidationException("Já existe uma Pessoa Física cadastrada com este CPF");
        }
        PessoaFisicaEntity entity = toEntity(pessoaFisicaDTO);
        return pessoaFisicaRepository.save(entity);
    }

    @Override
    public PessoaFisicaEntity update(PessoaFisicaDTO pessoaFisicaDTO) throws ValidationException {
        if (pessoaFisicaDTO == null || pessoaFisicaDTO.getId() == null) {
            throw new ValidationException("Pessoa Física não pode ser nula e deve ter um ID válido");
        }
        PessoaFisicaEntity entity = pessoaFisicaRepository.findById(pessoaFisicaDTO.getId())
                .orElseThrow(() -> new ValidationException("Pessoa Física não encontrada para ID: " + pessoaFisicaDTO.getId()));

        if (pessoaFisicaDTO.getNome() != null) entity.setNome(pessoaFisicaDTO.getNome());
        if (pessoaFisicaDTO.getRg() != null) entity.setRg(pessoaFisicaDTO.getRg());
        if (pessoaFisicaDTO.getSexo() != null) entity.setSexo(pessoaFisicaDTO.getSexo());
        if (pessoaFisicaDTO.getDataNascimento() != null) entity.setDataNascimento(pessoaFisicaDTO.getDataNascimento());
        if (pessoaFisicaDTO.getEstadoCivil() != null) entity.setEstadoCivil(pessoaFisicaDTO.getEstadoCivil());
        if (pessoaFisicaDTO.getNomeMae() != null) entity.setNomeMae(pessoaFisicaDTO.getNomeMae());
        if (pessoaFisicaDTO.getNomePai() != null) entity.setNomePai(pessoaFisicaDTO.getNomePai());
        if (pessoaFisicaDTO.getTelefoneFixo() != null) entity.setTelefoneFixo(pessoaFisicaDTO.getTelefoneFixo());
        if (pessoaFisicaDTO.getTelefone() != null) entity.setTelefone(pessoaFisicaDTO.getTelefone());

        return pessoaFisicaRepository.save(entity);
    }

    @Override
    public boolean deleteByCpf(String cpf) throws ValidationException {
        if (cpf == null || cpf.isEmpty()) {
            throw new ValidationException("CPF não pode ser nulo ou vazio");
        }
        PessoaFisicaEntity pessoaFisicaEntity = pessoaFisicaRepository.findByCpf(cpf);
        if (pessoaFisicaEntity == null) {
            throw new ValidationException("Pessoa Física não encontrada para CPF: " + cpf);
        }
        pessoaFisicaRepository.delete(pessoaFisicaEntity);
        return true;
    }


    private PessoaFisicaEntity toEntity(PessoaFisicaDTO pessoaFisicaDTO) throws ValidationException {

        PessoaFisicaEntity entity = new PessoaFisicaEntity();
        entity.setTipo(pessoaFisicaDTO.getTipo() != null ? pessoaFisicaDTO.getTipo() : "F");
        entity.setNome(pessoaFisicaDTO.getNome());
        entity.setCpf(pessoaFisicaDTO.getCpf());
        entity.setRg(pessoaFisicaDTO.getRg());
        entity.setSexo(pessoaFisicaDTO.getSexo());
        entity.setDataNascimento(pessoaFisicaDTO.getDataNascimento());
        entity.setEstadoCivil(pessoaFisicaDTO.getEstadoCivil());
        entity.setNomeMae(pessoaFisicaDTO.getNomeMae());
        entity.setNomePai(pessoaFisicaDTO.getNomePai());
        entity.setTelefoneFixo(pessoaFisicaDTO.getTelefoneFixo());
        entity.setTelefone(pessoaFisicaDTO.getTelefone());
        return entity;

    }

}
