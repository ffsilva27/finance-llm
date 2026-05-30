package com.study.llm.infrastructure.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.study.llm.infrastructure.persistence.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    @Query("select c from CustomerEntity c where c.serialNumberIdentification = :serialNumberIdentification")
    public Optional<CustomerEntity> findBySerialNumberIdentification(@Param("serialNumberIdentification") String serialNumberIdentification);

}
