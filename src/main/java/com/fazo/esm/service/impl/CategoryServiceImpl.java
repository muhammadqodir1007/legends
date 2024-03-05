package com.fazo.esm.service.impl;

import com.fazo.esm.entity.Category;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.CategoryDto;
import com.fazo.esm.repository.CategoryRepository;
import com.fazo.esm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        return ApiResponse.successResponse(allCategories);
    }

    @Override
    public ApiResponse<Category> getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
        return ApiResponse.successResponse(category);
    }

    @Override
    public ApiResponse<Category> createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        Category savedCategory = categoryRepository.save(category);
        return ApiResponse.successResponse(savedCategory, "Category created successfully");
    }

    @Override
    public ApiResponse<Category> updateCategory(int id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with id: " + id));
        existingCategory.setName(categoryDto.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return ApiResponse.successResponse(updatedCategory, "Category updated successfully");
    }

    @Override
    public ApiResponse<Void> deleteCategory(int id) {
        categoryRepository.deleteById(id);
        return ApiResponse.successResponse("Category deleted successfully");
    }
}
