package com.fazo.esm.service;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.MaterialDto;
import com.fazo.esm.payload.response.MaterialResponse;

import java.util.List;

public interface MaterialService {

    ApiResponse<List<MaterialResponse>> getAll();

    ApiResponse<List<MaterialResponse>> search(String name);

    ApiResponse<MaterialResponse> getById(int id);

    ApiResponse<MaterialResponse> insert(MaterialDto materialDto);

    ApiResponse<List<MaterialResponse>> getAllByCategoryId(int id);

    ApiResponse<MaterialResponse> delete(MaterialDto materialDto);
}
