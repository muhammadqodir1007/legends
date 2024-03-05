// MaterialCategoryService.java
package com.fazo.esm.service;

import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.MaterialCategoryDto;

import java.util.List;

public interface MaterialCategoryService {

    ApiResponse<List<MaterialCategory>> getAll();

    ApiResponse<MaterialCategory> getById(int id);

    ApiResponse<MaterialCategory> create(MaterialCategoryDto materialCategoryDto);

    ApiResponse<MaterialCategory> update(int id, MaterialCategoryDto materialCategoryDto);

    ApiResponse<String> delete(int id);
}
