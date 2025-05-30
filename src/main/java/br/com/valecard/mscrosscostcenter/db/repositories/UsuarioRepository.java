package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    @Query(value = "SELECT u.id AS id, u.nome AS nome, u.email AS email FROM USUARIO u WHERE u.id = :userId", nativeQuery = true)
    List<Object[]> findUserBasicDataById(@Param("userId") Integer userId);
}
