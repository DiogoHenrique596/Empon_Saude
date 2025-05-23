package br.com.valecard.mscrosscostcenter.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.valecard.mscrosscostcenter.db.entities.AdcClientCostCenterEntity;

import java.util.List;

@Repository
public interface CostCenterRepository extends JpaRepository<AdcClientCostCenterEntity, Long>,
        JpaSpecificationExecutor<AdcClientCostCenterEntity> {

    @Query(value = "SELECT c.filial AS branch,  c.CODIGO AS code, c.NOME AS description FROM ADC_CLIENTES c WHERE c.ID = :clientId ", nativeQuery = true)
    public List<Object[]> findByClientIdSeedData(@Param("clientId") Long clientId);

}
