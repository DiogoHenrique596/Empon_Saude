package br.com.valecard.mscostcenter.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.valecard.mscostcenter.db.entities.AdcClientCostCenterEntity;

@Repository
public interface CostCenterRepository extends JpaRepository<AdcClientCostCenterEntity, Long>,
        JpaSpecificationExecutor<AdcClientCostCenterEntity> {

}
