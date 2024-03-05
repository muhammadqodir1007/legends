package com.fazo.esm.service;

import com.fazo.esm.entity.MaterialType;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.MaterialTypeDto;

import java.util.List;

public interface MaterialTypeService {

    ApiResponse<List<MaterialType>> getAll();

    ApiResponse<MaterialType> getById(int id);

    ApiResponse<MaterialType> create(MaterialTypeDto materialTypeDto);

    ApiResponse<MaterialType> update(int id, MaterialTypeDto materialTypeDto);

    ApiResponse<String> delete(int id);
}
