package com.fazo.esm.repository;

import com.fazo.esm.entity.Material;
import com.fazo.esm.entity.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {


    boolean existsByMaterialType(MaterialType itemType);

    Optional<Material> findByMaterialType(MaterialType itemType);

    List<Material> findAllByMaterialCategoryId(int id);


    List<Material> findByMaterialTypeNameContaining(String name);


}
