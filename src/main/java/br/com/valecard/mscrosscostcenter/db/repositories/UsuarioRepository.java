package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query("SELECT u.id, u.nome, u.email FROM UsuarioEntity u WHERE u.id = :userId")
    List<Object[]> findUserBasicDataById(@Param("userId") Integer userId);
}
