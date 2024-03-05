package com.fazo.esm.repository;

import com.fazo.esm.entity.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, Integer> {

    Optional<MaterialType> findByName(String name);



}
