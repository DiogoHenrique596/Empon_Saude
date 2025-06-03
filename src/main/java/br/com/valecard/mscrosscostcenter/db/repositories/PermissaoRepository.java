package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository extends JpaRepository<PermissaoEntity, Integer> {
    @Query("SELECT p.id, p.nome FROM PermissaoEntity p WHERE p.id = :permissaoId")
    List<Object[]> findPermissaoBasicDataById(@Param("permissaoId") Integer permissaoId);
}
