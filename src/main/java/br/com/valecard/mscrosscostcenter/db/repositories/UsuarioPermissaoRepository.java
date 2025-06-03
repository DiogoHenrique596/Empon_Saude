package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoEntity;
import br.com.valecard.mscrosscostcenter.db.entities.UsuarioPermissaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissaoEntity, UsuarioPermissaoId> {

    @Query("SELECT up.usuario.id, up.permissao.id FROM UsuarioPermissaoEntity up WHERE up.usuario.id = :userId")
    List<Object[]> findPermissoesByUsuarioId(@Param("userId") Integer userId);

    void deleteByUsuarioId(Integer id);
}