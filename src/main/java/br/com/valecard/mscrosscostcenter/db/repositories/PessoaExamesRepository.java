package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesEntity;
import br.com.valecard.mscrosscostcenter.db.entities.PessoaExamesId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaExamesRepository extends JpaRepository<PessoaExamesEntity, PessoaExamesId> {

    @Query("SELECT pe FROM PessoaExamesEntity pe WHERE pe.id = :id")
    Optional<PessoaExamesEntity> findById(PessoaExamesId id);

    List<PessoaExamesEntity> findByPessoaFisicaId(Integer pessoaFisicaId);
}
