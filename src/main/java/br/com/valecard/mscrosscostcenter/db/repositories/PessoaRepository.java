package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer> {
    @Query("SELECT p FROM PessoaEntity p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<PessoaEntity> findByNomeContainingIgnoreCase(@Param("nome") String nome);
}
