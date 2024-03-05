package com.fazo.esm.service;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.ItemDto;
import com.fazo.esm.payload.response.ItemResponse;

import java.util.List;

public interface ItemService {
    ApiResponse<List<ItemResponse>> search(String name);
    ApiResponse<List<ItemResponse>> getAll();
    ApiResponse<ItemResponse> getById(int id);
    ApiResponse<?> insert(ItemDto itemDto);
    ApiResponse<List<ItemResponse>> getAllByCategory(int categoryId);
    ApiResponse<?> delete(ItemDto itemDto);
}
