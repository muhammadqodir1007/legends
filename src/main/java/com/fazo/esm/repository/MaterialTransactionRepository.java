package com.fazo.esm.repository;

import com.fazo.esm.entity.MaterialTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialTransactionRepository extends JpaRepository<MaterialTransaction, Integer> {
    List<MaterialTransaction> findAllByUserId(int id);

}
