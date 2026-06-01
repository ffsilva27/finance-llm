package com.study.llm.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.llm.infrastructure.persistence.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID>{
    @Query("select t from TransactionEntity t where t.customer.id = :customerId order by t.effectiveDate DESC")
    public List<TransactionEntity> findLastFiveRecentsTransactionByCustomerId(@Param("customerId") UUID customerId, Pageable pageable);
}
