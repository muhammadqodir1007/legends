// ItemTransactionService interface
package com.fazo.esm.service;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.ItemDto;
import com.fazo.esm.payload.response.ItemTransactionRes;

import java.util.List;

public interface ItemTransactionService {
    void add(ItemDto item, String actionType);

    ApiResponse<ItemTransactionRes> getById(int id);

    ApiResponse<List<ItemTransactionRes>> getByAdminId(int adminId);

    ApiResponse<List<ItemTransactionRes>> getAll();

    ApiResponse<?> delete(ItemDto item, String actionType);
}