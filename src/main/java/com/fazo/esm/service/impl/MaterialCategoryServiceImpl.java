// MaterialCategoryServiceImpl.java
package com.fazo.esm.service.impl;

import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.MaterialCategoryDto;
import com.fazo.esm.repository.MaterialCategoryRepository;
import com.fazo.esm.service.MaterialCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialCategoryServiceImpl implements MaterialCategoryService {

    private final MaterialCategoryRepository materialCategoryRepository;

    @Override
    public ApiResponse<List<MaterialCategory>> getAll() {
        List<MaterialCategory> materialCategories = materialCategoryRepository.findAll();
        return ApiResponse.successResponse(materialCategories);
    }

    @Override
    public ApiResponse<MaterialCategory> getById(int id) {
        MaterialCategory materialCategory = materialCategoryRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Material category not found with id: " + id));
        return ApiResponse.successResponse(materialCategory);
    }

    @Override
    public ApiResponse<MaterialCategory> create(MaterialCategoryDto materialCategoryDto) {
        MaterialCategory materialCategory = new MaterialCategory();
        materialCategory.setName(materialCategoryDto.getName());
        MaterialCategory savedCategory = materialCategoryRepository.save(materialCategory);
        return ApiResponse.successResponse(savedCategory, "Material category created successfully");
    }

    @Override
    public ApiResponse<MaterialCategory> update(int id, MaterialCategoryDto materialCategoryDto) {
        MaterialCategory materialCategory = materialCategoryRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Material category not found with id: " + id));
        materialCategory.setName(materialCategoryDto.getName());
        MaterialCategory updatedCategory = materialCategoryRepository.save(materialCategory);
        return ApiResponse.successResponse(updatedCategory, "Material category updated successfully");
    }

    @Override
    public ApiResponse<String> delete(int id) {
        if (materialCategoryRepository.existsById(id)) {
            materialCategoryRepository.deleteById(id);
            return ApiResponse.successResponse("Material category deleted successfully");
        } else {
            throw RestException.restThrow("Material category not found with id: " + id);
        }
    }
}
