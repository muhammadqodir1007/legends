package com.fazo.esm.repository;

import com.fazo.esm.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {


    Optional<ItemType> findByName(String name);
    List<ItemType> findAllByCategoryId(Integer categoryId);

}
