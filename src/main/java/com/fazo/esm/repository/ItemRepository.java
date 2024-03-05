package com.fazo.esm.repository;

import com.fazo.esm.entity.Item;
import com.fazo.esm.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findAllByCategoryId(int id);

    List<Item> findByItemTypeNameContaining(String name);


    boolean existsByItemType(ItemType itemType);

    Optional<Item> findByItemType(ItemType itemType);

}
