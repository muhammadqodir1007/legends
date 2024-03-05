package com.fazo.esm.service;

import com.fazo.esm.entity.Category;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    ApiResponse<List<Category>> getAllCategories();

    ApiResponse<Category> getCategoryById(int id);

    ApiResponse<Category> createCategory(CategoryDto categoryDto);

    ApiResponse<Category> updateCategory(int id, CategoryDto categoryDto);

    ApiResponse<Void> deleteCategory(int id);
}
