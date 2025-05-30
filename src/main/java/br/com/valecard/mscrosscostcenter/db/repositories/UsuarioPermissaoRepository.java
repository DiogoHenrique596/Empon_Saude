package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissaoEntity, Integer> {

    @Query(value = "SELECT up.usuario_id AS usuarioId, up.permissao_id AS permissaoId FROM USUARIO_PERMISSAO up WHERE up.usuario_id = :userId", nativeQuery = true)
    List<Object[]> findPermissoesByUsuarioId(@Param("userId") Integer userId);
}