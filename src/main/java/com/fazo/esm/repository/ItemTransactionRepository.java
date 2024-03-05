package com.fazo.esm.repository;

import com.fazo.esm.entity.ItemTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemTransactionRepository extends JpaRepository<ItemTransaction, Integer> {


    List<ItemTransaction> findAllByUserId(int id);
}
