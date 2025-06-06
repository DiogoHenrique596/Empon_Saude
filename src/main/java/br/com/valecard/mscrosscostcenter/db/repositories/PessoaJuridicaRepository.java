package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaJuridicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaEntity, Integer> {

    @Query("SELECT pj FROM PessoaJuridicaEntity pj WHERE pj.cnpj = :cnpj")
    PessoaJuridicaEntity findByCnpj(@Param("cnpj") String cnpj);
}
