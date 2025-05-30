package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.PermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepository extends JpaRepository<PermissaoEntity, Integer> {
    @Query(value = "SELECT p.id AS id, p.nome AS nome, p.descricao AS descricao FROM PERMISSAO p WHERE p.id = :permissaoId", nativeQuery = true)
    List<Object[]> findPermissaoBasicDataById(@Param("permissaoId") Integer permissaoId);
}
