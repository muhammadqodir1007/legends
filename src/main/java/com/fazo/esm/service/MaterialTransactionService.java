package com.fazo.esm.service;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.MaterialDto;
import com.fazo.esm.payload.response.MaterialTransactionRes;

import java.util.List;

public interface MaterialTransactionService {

    void add(MaterialDto materialDto, String actionType);

    ApiResponse<MaterialTransactionRes> getById(int id);

    ApiResponse<List<MaterialTransactionRes>> getByUserId(int userId);

    ApiResponse<List<MaterialTransactionRes>> getAll();

    ApiResponse<String> delete(MaterialDto materialDto, String actionType);
}
