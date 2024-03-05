package com.fazo.esm.service.impl;

import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.entity.MaterialTransaction;
import com.fazo.esm.entity.MaterialType;
import com.fazo.esm.entity.User;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.dto.MaterialDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.MaterialTransactionRes;
import com.fazo.esm.repository.MaterialCategoryRepository;
import com.fazo.esm.repository.MaterialTransactionRepository;
import com.fazo.esm.repository.MaterialTypeRepository;
import com.fazo.esm.repository.UserRepository;
import com.fazo.esm.service.MaterialTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialTransactionServiceImpl implements MaterialTransactionService {

    private final MaterialTransactionRepository materialTransactionRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final MaterialCategoryRepository materialCategoryRepository;
    private final UserRepository userRepository;

    @Override
    public void add(MaterialDto materialDto, String actionType) {
        MaterialCategory category = materialCategoryRepository.findById(materialDto.getMaterialCategoryId())
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        User user = userRepository.findById(materialDto.getAdminId())
                .orElseThrow(() -> RestException.restThrow("User not found"));
        MaterialType materialType = materialTypeRepository.findById(materialDto.getMaterialTypeId())
                .orElseThrow(() -> RestException.restThrow("Material Type not found"));

        MaterialTransaction materialTransaction = new MaterialTransaction();
        materialTransaction.setMaterialType(materialType);
        materialTransaction.setCategory(category);
        materialTransaction.setUser(user);
        materialTransaction.setQuantity(materialDto.getQuantity());
        materialTransaction.setActionType(actionType);
        materialTransaction.setActionDate(LocalDateTime.now());
        materialTransactionRepository.save(materialTransaction);
    }

    @Override
    public ApiResponse<MaterialTransactionRes> getById(int id) {
        MaterialTransaction materialTransaction = materialTransactionRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Transaction not found with this ID"));
        MaterialTransactionRes materialTransactionRes = mapMaterialTransactionToResponse(materialTransaction);
        return ApiResponse.successResponse(materialTransactionRes);
    }

    @Override
    public ApiResponse<List<MaterialTransactionRes>> getByUserId(int userId) {
        List<MaterialTransaction> allByUserId = materialTransactionRepository.findAllByUserId(userId);
        List<MaterialTransactionRes> materialTransactionResList = allByUserId.stream()
                .map(this::mapMaterialTransactionToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(materialTransactionResList);
    }

    @Override
    public ApiResponse<List<MaterialTransactionRes>> getAll() {
        List<MaterialTransaction> allMaterialTransactions = materialTransactionRepository.findAll();
        List<MaterialTransactionRes> materialTransactionResList = allMaterialTransactions.stream()
                .map(this::mapMaterialTransactionToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(materialTransactionResList);
    }

    @Override
    public ApiResponse<String> delete(MaterialDto materialDto, String actionType) {
        MaterialCategory materialCategory = materialCategoryRepository.findById(materialDto.getMaterialCategoryId())
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        User user = userRepository.findById(materialDto.getAdminId())
                .orElseThrow(() -> RestException.restThrow("User not found"));
        MaterialType materialType = materialTypeRepository.findById(materialDto.getMaterialTypeId())
                .orElseThrow(() -> RestException.restThrow("Material Type not found"));

        MaterialTransaction materialTransaction = new MaterialTransaction();
        materialTransaction.setMaterialType(materialType);
        materialTransaction.setCategory(materialCategory);
        materialTransaction.setUser(user);
        materialTransaction.setQuantity(materialDto.getQuantity());
        materialTransaction.setActionDate(LocalDateTime.now());
        materialTransaction.setActionType(actionType);
        materialTransactionRepository.save(materialTransaction);

        return ApiResponse.successResponse("Deleted successfully");
    }

    private MaterialTransactionRes mapMaterialTransactionToResponse(MaterialTransaction materialTransaction) {
        MaterialTransactionRes materialTransactionRes = new MaterialTransactionRes();
        materialTransactionRes.setId(materialTransaction.getId());
        materialTransactionRes.setMaterialTypeId(materialTransaction.getMaterialType().getId());
        materialTransactionRes.setUserId(materialTransaction.getUser().getId());
        materialTransactionRes.setCategoryId(materialTransaction.getCategory().getId());
        materialTransactionRes.setQuantity(materialTransaction.getQuantity());
        materialTransactionRes.setActionType(materialTransaction.getActionType());
        materialTransactionRes.setActionDate(materialTransaction.getActionDate());
        return materialTransactionRes;
    }
}
