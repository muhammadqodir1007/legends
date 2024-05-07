package com.fazo.esm.service.impl;

import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.entity.MaterialType;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.dto.MaterialTypeDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.repository.MaterialCategoryRepository;
import com.fazo.esm.repository.MaterialTypeRepository;
import com.fazo.esm.service.MaterialTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialTypeServiceImpl implements MaterialTypeService {

    private final MaterialTypeRepository materialTypeRepository;
    private final MaterialCategoryRepository materialCategoryRepository;

    @Override
    public ApiResponse<List<MaterialType>> getAll() {
        List<MaterialType> materialTypes = materialTypeRepository.findAll();
        return ApiResponse.successResponse(materialTypes);
    }

    @Override
    public ApiResponse<MaterialType> getById(int id) {
        MaterialType materialType = materialTypeRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Material type not found with id: " + id));
        return ApiResponse.successResponse(materialType);
    }

    @Override
    public ApiResponse<List<MaterialType>> getByCategoryId(int categoryId) {

        List<MaterialType> byMaterialCategoryId = materialTypeRepository.findByMaterialCategoryId(categoryId);

        return ApiResponse.successResponse(byMaterialCategoryId);


    }

    @Override
    public ApiResponse<MaterialType> create(MaterialTypeDto materialTypeDto) {
        MaterialType materialType = new MaterialType();
        MaterialCategory materialCategory = materialCategoryRepository.findById(materialTypeDto.getMaterialCategoryId()).orElseThrow(NullPointerException::new);
        materialType.setMaterialCategory(materialCategory);
        materialType.setName(materialTypeDto.getName());
        MaterialType savedType = materialTypeRepository.save(materialType);
        return ApiResponse.successResponse(savedType, "Material type created successfully");
    }

    @Override
    public ApiResponse<MaterialType> update(int id, MaterialTypeDto materialTypeDto) {
        MaterialType materialType = materialTypeRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Material type not found with id: " + id));
        materialType.setName(materialTypeDto.getName());
        MaterialType updatedType = materialTypeRepository.save(materialType);
        return ApiResponse.successResponse(updatedType, "Material type updated successfully");
    }

    @Override
    public ApiResponse<String> delete(int id) {
        if (materialTypeRepository.existsById(id)) {
            materialTypeRepository.deleteById(id);
            return ApiResponse.successResponse("Material type deleted successfully");
        } else {
            throw RestException.restThrow("Material type not found with id: " + id);
        }
    }
}
