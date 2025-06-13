package br.com.valecard.mscrosscostcenter.db.repositories;

import br.com.valecard.mscrosscostcenter.db.entities.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExameRepository extends JpaRepository<ExameEntity, Integer> {

    @Query("SELECT e FROM ExameEntity e WHERE e.id = :id")
    ExameEntity findByid(Integer id);
}
