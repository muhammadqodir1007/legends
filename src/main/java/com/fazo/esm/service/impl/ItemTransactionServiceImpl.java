package com.fazo.esm.service.impl;

import com.fazo.esm.entity.Category;
import com.fazo.esm.entity.ItemTransaction;
import com.fazo.esm.entity.ItemType;
import com.fazo.esm.entity.User;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.ItemDto;
import com.fazo.esm.payload.response.ItemTransactionRes;
import com.fazo.esm.repository.CategoryRepository;
import com.fazo.esm.repository.ItemTransactionRepository;
import com.fazo.esm.repository.ItemTypeRepository;
import com.fazo.esm.repository.UserRepository;
import com.fazo.esm.service.ItemTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemTransactionServiceImpl implements ItemTransactionService {

    private final ItemTransactionRepository itemTransactionRepository;
    private final CategoryRepository categoryRepository;
    private final ItemTypeRepository itemTypeRepository;
    private final UserRepository userRepository;

    @Override
    public void add(ItemDto item, String actionType) {
        ItemTransaction itemTransaction = mapItemDtoToTransaction(item, actionType);
        itemTransactionRepository.save(itemTransaction);
    }

    @Override
    public ApiResponse<ItemTransactionRes> getById(int id) {
        ItemTransaction itemTransaction = itemTransactionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Transaction not found with this ID"));
        ItemTransactionRes itemTransactionRes = mapItemTransactionToResponse(itemTransaction);
        return ApiResponse.successResponse(itemTransactionRes);
    }

    @Override
    public ApiResponse<List<ItemTransactionRes>> getByAdminId(int adminId) {
        List<ItemTransaction> list = itemTransactionRepository.findAllByUserId(adminId);
        List<ItemTransactionRes> collect = list.stream().map(this::mapItemTransactionToResponse).toList();
        return ApiResponse.successResponse(collect);
    }

    @Override
    public ApiResponse<List<ItemTransactionRes>> getAll() {
        List<ItemTransaction> allTransactions = itemTransactionRepository.findAll();
        List<ItemTransactionRes> collect = allTransactions.stream().map(this::mapItemTransactionToResponse).toList();
        return ApiResponse.successResponse(collect);
    }

    @Override
    public ApiResponse<?> delete(ItemDto item, String actionType) {
        ItemTransaction itemTransaction = mapItemDtoToTransaction(item, actionType);
        itemTransactionRepository.save(itemTransaction);
        return ApiResponse.successResponse("Deleted successfully");
    }


    private ItemTransactionRes mapItemTransactionToResponse(ItemTransaction itemTransaction) {
        ItemTransactionRes itemTransactionRes = new ItemTransactionRes();
        itemTransactionRes.setId(itemTransaction.getId());
        itemTransactionRes.setItemTypeId(itemTransaction.getItemType().getId());
        itemTransactionRes.setQuantity(itemTransaction.getQuantity());
        itemTransactionRes.setUserId(itemTransaction.getUser().getId());
        itemTransactionRes.setCategoryId(itemTransaction.getCategory().getId());
        itemTransactionRes.setActionType(itemTransaction.getActionType());
        itemTransactionRes.setActionDate(itemTransaction.getActionDate());
        return itemTransactionRes;
    }

    private ItemTransaction mapItemDtoToTransaction(ItemDto item, String actionType) {
        Category category = categoryRepository.findById(item.getCategoryId())
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        User user = userRepository.findById(item.getAdminId())
                .orElseThrow(() -> RestException.restThrow("User not found"));
        ItemType itemType = itemTypeRepository.findById(item.getItemType())
                .orElseThrow(() -> RestException.restThrow("Item Type not found"));

        ItemTransaction itemTransaction = new ItemTransaction();
        itemTransaction.setItemType(itemType);
        itemTransaction.setCategory(category);
        itemTransaction.setUser(user);
        itemTransaction.setQuantity(item.getQuantity());
        itemTransaction.setActionType(actionType);
        itemTransaction.setActionDate(LocalDateTime.now());
        return itemTransaction;
    }
}