package com.study.llm.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.llm.infrastructure.persistence.entity.BalanceEntity;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity, UUID>{

    @Query("select b from BalanceEntity b where b.customer.id = :customerId")
    public Optional<BalanceEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
