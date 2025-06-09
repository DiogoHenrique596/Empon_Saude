package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaJuridicaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaJuridicaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.PessoaJuridicaService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.PessoaJuridicaDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PessoaJuridicaServiceImpl implements PessoaJuridicaService {


    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;


    @Override
    public PessoaJuridicaEntity findByCnpj(String cnpj) throws ValidationException {
        if (cnpj == null || cnpj.isEmpty()) {;
            throw new ValidationException("CNPJ não pode ser nulo ou vazio");
        }
        PessoaJuridicaEntity pessoaJuridicaEntity = pessoaJuridicaRepository.findByCnpj(cnpj);
        if (pessoaJuridicaEntity == null) {
            throw new ValidationException("Pessoa Juridica não encontrada para CNPJ: " + cnpj);
        }
        return pessoaJuridicaEntity;
    }

    @Override
    public List<PessoaJuridicaEntity> findAll() throws ValidationException {
        List<PessoaJuridicaEntity> pessoasJuridicas = pessoaJuridicaRepository.findAll();
        if (pessoasJuridicas.isEmpty()) {
            throw new ValidationException("Nenhuma Pessoa Juridica encontrada");
        }
        return pessoasJuridicas;
    }

    @Override
    public PessoaJuridicaEntity save(PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException {
        if (pessoaJuridicaDTO == null) {;
            throw new ValidationException("Pessoa Juridica não pode ser nula");
        }
        if (pessoaJuridicaDTO.getCnpj() == null || pessoaJuridicaDTO.getCnpj().isEmpty()) {
            throw new ValidationException("CNPJ não pode ser nulo ou vazio");
        }
        if (pessoaJuridicaRepository.findByCnpj(pessoaJuridicaDTO.getCnpj()) != null) {
            throw new ValidationException("Já existe uma Pessoa Jurídica com este CNPJ");
        }
        PessoaJuridicaEntity entity = new PessoaJuridicaEntity();
        toEntity(entity, pessoaJuridicaDTO);
        return pessoaJuridicaRepository.save(entity);
    }

    @Override
    public PessoaJuridicaEntity update(PessoaJuridicaDTO pessoaJuridicaDTO) throws ValidationException {
        if (pessoaJuridicaDTO == null || pessoaJuridicaDTO.getId() == null) {
            throw new ValidationException("Pessoa Juridica não pode ser nula e deve ter um ID válido");
        }

        PessoaJuridicaEntity newPessoaJuridicaEntity = pessoaJuridicaRepository.findById(pessoaJuridicaDTO.getId())
                .orElseThrow(() -> new ValidationException("Pessoa Juridica não encontrada para o ID: " + pessoaJuridicaDTO.getId()));


        newPessoaJuridicaEntity.setRazaoSocial(pessoaJuridicaDTO.getRazaoSocial());
        newPessoaJuridicaEntity.setNomeFantasia(pessoaJuridicaDTO.getNomeFantasia());
        newPessoaJuridicaEntity.setInscricaoEstadual(pessoaJuridicaDTO.getInscricaoEstadual());
        newPessoaJuridicaEntity.setInscricaoMunicipal(pessoaJuridicaDTO.getInscricaoMunicipal());
        newPessoaJuridicaEntity.setTelefone(pessoaJuridicaDTO.getTelefone());
        newPessoaJuridicaEntity.setTelefoneFixo(pessoaJuridicaDTO.getTelefoneFixo());

        return pessoaJuridicaRepository.save(newPessoaJuridicaEntity);
    }


    @Override
    public boolean deleteByCnpj(String cnpj) throws ValidationException {
        PessoaJuridicaEntity entity = findByCnpj(cnpj);
        pessoaJuridicaRepository.delete(entity);
        return true;
    }
    private void toEntity(PessoaJuridicaEntity entity, PessoaJuridicaDTO pessoaJuridicaDTO) {

        String nome = pessoaJuridicaDTO.getNome();
        if (nome == null || nome.trim().isEmpty()) {
            nome = pessoaJuridicaDTO.getRazaoSocial();
        }
        entity.setNome(nome);
        entity.setTipo("J");
        entity.setCnpj(pessoaJuridicaDTO.getCnpj());
        entity.setRazaoSocial(pessoaJuridicaDTO.getRazaoSocial());
        entity.setNomeFantasia(pessoaJuridicaDTO.getNomeFantasia());
        entity.setInscricaoEstadual(pessoaJuridicaDTO.getInscricaoEstadual());
        entity.setInscricaoMunicipal(pessoaJuridicaDTO.getInscricaoMunicipal());
        entity.setDataAbertura(LocalDate.parse(pessoaJuridicaDTO.getDataAbertura()));
        entity.setTelefone(pessoaJuridicaDTO.getTelefone());
        entity.setTelefoneFixo(pessoaJuridicaDTO.getTelefoneFixo());

    }


}
