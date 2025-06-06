package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PessoaFisicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaEntity, Integer> {

    @Query("SELECT pf FROM PessoaFisicaEntity pf WHERE pf.cpf = :cpf")
    PessoaFisicaEntity findByCpf(@Param("cpf") String cpf);
}
