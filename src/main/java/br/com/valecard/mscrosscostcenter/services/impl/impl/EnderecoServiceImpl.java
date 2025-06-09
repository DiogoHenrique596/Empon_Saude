package br.com.valecard.mscrosscostcenter.services.impl.impl;

import br.com.valecard.mscrosscostcenter.db.entities.EnderecoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import br.com.valecard.mscrosscostcenter.db.repositories.EnderecoRepository;
import br.com.valecard.mscrosscostcenter.db.repositories.PessoaRepository;
import br.com.valecard.mscrosscostcenter.services.impl.EnderecoService;
import br.com.valecard.mscrosscostcenter.services.impl.dtos.EnderecoDTO;
import br.com.valecard.mscrosscostcenter.services.impl.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public List<EnderecoEntity> findByPessoaId(Integer pessoaId) throws ValidationException {
        if (pessoaId == null) {
            throw new ValidationException("pessoa_id não pode ser nulo");
        }
        return enderecoRepository.findByPessoaId(pessoaId);
    }

    @Override
    public List<EnderecoEntity> findAll() throws ValidationException {
        List<EnderecoEntity> enderecos = enderecoRepository.findAll();
        if (enderecos.isEmpty()) {
            throw new ValidationException("Nenhum endereço encontrado");
        }
        return enderecos;
    }

    @Override
    public EnderecoEntity save(EnderecoDTO enderecoDTO) throws ValidationException {

        Assert.notNull(enderecoDTO, "enderecoDTO não pode ser nulo");

        EnderecoEntity entity = toEntity(enderecoDTO, pessoaRepository);
        if (entity.getLogradouro() == null || entity.getCidade() == null || entity.getEstado() == null || entity.getCep() == null) {
            throw new ValidationException("Campos obrigatórios não podem ser nulos");
        }
        // Regra para não permitir duplicidade de endereço para a mesma pessoa
        List<EnderecoEntity> existentes = enderecoRepository.findByPessoaId(entity.getPessoa().getId());
        boolean duplicado = existentes.stream().anyMatch(e ->
                e.getLogradouro().equalsIgnoreCase(entity.getLogradouro()) &&
                        ((e.getNumero() == null && entity.getNumero() == null) || (e.getNumero() != null && e.getNumero().equals(entity.getNumero()))) &&
                        ((e.getBairro() == null && entity.getBairro() == null) || (e.getBairro() != null && e.getBairro().equalsIgnoreCase(entity.getBairro()))) &&
                        e.getCidade().equalsIgnoreCase(entity.getCidade()) &&
                        e.getEstado().equalsIgnoreCase(entity.getEstado()) &&
                        e.getCep().equals(entity.getCep())
        );
        if (duplicado) {
            throw new ValidationException("Já existe um endereço igual para esta pessoa");
        }
        return enderecoRepository.save(entity);
    }

    @Override
    public EnderecoEntity update(EnderecoDTO enderecoDTO) throws ValidationException {
        Assert.notNull(enderecoDTO.getId(), "id do endereço não pode ser nulo");
        EnderecoEntity existente = enderecoRepository.findById(enderecoDTO.getId())
                .orElseThrow(() -> new ValidationException("Endereço não encontrado com o ID: " + enderecoDTO.getId()));

        if (enderecoDTO.getLogradouro() != null) existente.setLogradouro(enderecoDTO.getLogradouro());
        if (enderecoDTO.getNumero() != null) existente.setNumero(enderecoDTO.getNumero());
        if (enderecoDTO.getComplemento() != null) existente.setComplemento(enderecoDTO.getComplemento());
        if (enderecoDTO.getBairro() != null) existente.setBairro(enderecoDTO.getBairro());
        if (enderecoDTO.getCidade() != null) existente.setCidade(enderecoDTO.getCidade());
        if (enderecoDTO.getEstado() != null) existente.setEstado(enderecoDTO.getEstado());
        if (enderecoDTO.getCep() != null) existente.setCep(enderecoDTO.getCep());

        return enderecoRepository.save(existente);
    }

    @Override
    public boolean deleteById(Integer id) throws ValidationException {
        Assert.notNull(id, "id não pode ser nulo");
        EnderecoEntity entity = enderecoRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Endereço não encontrado com o ID: " + id));
        enderecoRepository.deleteById(entity.getId());
        return true;
    }

    public static EnderecoEntity toEntity(EnderecoDTO dto, PessoaRepository pessoaRepository) {
        if (dto == null) {
            return null;
        }
        EnderecoEntity entity = new EnderecoEntity();
        entity.setId(dto.getId());
        if (dto.getPessoaId() != null) {
            PessoaEntity pessoa = pessoaRepository.findById(dto.getPessoaId()).orElse(null);
            entity.setPessoa(pessoa);
        }
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        return entity;
    }
}
