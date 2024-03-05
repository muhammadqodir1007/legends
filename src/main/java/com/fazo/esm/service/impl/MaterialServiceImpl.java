package com.fazo.esm.service.impl;

import com.fazo.esm.entity.Material;
import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.entity.MaterialType;
import com.fazo.esm.entity.User;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.dto.MaterialDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.MaterialResponse;
import com.fazo.esm.repository.MaterialCategoryRepository;
import com.fazo.esm.repository.MaterialRepository;
import com.fazo.esm.repository.MaterialTypeRepository;
import com.fazo.esm.repository.UserRepository;
import com.fazo.esm.service.MaterialService;
import com.fazo.esm.service.MaterialTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final MaterialCategoryRepository materialCategoryRepository;
    private final MaterialTransactionService materialTransactionService;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<List<MaterialResponse>> getAll() {
        List<Material> list = materialRepository.findAll();
        List<MaterialResponse> collect = list.stream().map(this::mapItemToResponse).toList();
        return ApiResponse.successResponse(collect);
    }

    @Override
    public ApiResponse<List<MaterialResponse>> search(String name) {
        List<Material> materials = materialRepository.findByMaterialTypeNameContaining(name);
        List<MaterialResponse> responses = materials.stream()
                .map(this::mapItemToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(responses);
    }

    @Override
    public ApiResponse<MaterialResponse> getById(int id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> RestException.restThrow("Material not found"));
        return ApiResponse.successResponse(mapItemToResponse(material));
    }

    @Override
    public ApiResponse<MaterialResponse> insert(MaterialDto materialDto) {
        MaterialCategory category = materialCategoryRepository.findById(materialDto.getMaterialCategoryId()).orElseThrow(() -> RestException.restThrow("Category not found"));
        User user = userRepository.findById(materialDto.getAdminId()).orElseThrow(() -> RestException.restThrow("User not found"));
        MaterialType itemType = materialTypeRepository.findById(materialDto.getMaterialTypeId()).orElseThrow(() -> RestException.restThrow("Material Type not found"));
        boolean isExist = materialRepository.existsByMaterialType(itemType);
        Material material;
        if (isExist) {
            material = materialRepository.findByMaterialType(itemType).orElseThrow(() -> RestException.restThrow("material not found"));
            material.setQuantity(material.getQuantity() + materialDto.getQuantity());
            material.setUpdatedAt(LocalDateTime.now());
        } else {
            material = new Material();
            material.setMaterialType(itemType);
            material.setMaterialCategory(category);
            material.setDescription(materialDto.getDescription());
            material.setQuantity(materialDto.getQuantity());
            material.setCreatedAt(LocalDateTime.now());
        }
        material.setUser(user);

        materialRepository.save(material);
        materialTransactionService.add(materialDto, "added");
        return ApiResponse.successResponse(mapItemToResponse(material), "Material successfully saved");
    }


    @Override
    public ApiResponse<List<MaterialResponse>> getAllByCategoryId(int id) {
        List<Material> all = materialRepository.findAllByMaterialCategoryId(id);
        List<MaterialResponse> list = all.stream().map(this::mapItemToResponse).toList();
        return ApiResponse.successResponse(list);
    }

    @Override
    public ApiResponse<MaterialResponse> delete(MaterialDto materialDto) {
        MaterialType itemType1 = materialTypeRepository.findById(materialDto.getMaterialTypeId())
                .orElseThrow(() -> RestException.restThrow("item type not found"));
        User user = userRepository.findById(materialDto.getAdminId())
                .orElseThrow(() -> RestException.restThrow("user not found"));
        boolean exists = materialRepository.existsByMaterialType(itemType1);
        Material save;
        if (exists) {
            Material item = materialRepository.findByMaterialType(itemType1)
                    .orElseThrow(() -> RestException.restThrow("not found"));
            if (item.getQuantity() < materialDto.getQuantity())
                throw RestException.restThrow("there is not enough product");
            item.setQuantity(item.getQuantity() - materialDto.getQuantity());
            item.setUser(user);
            save = materialRepository.save(item);
        } else {
            throw RestException.restThrow("item not found");
        }
        materialTransactionService.delete(materialDto, "subtracted");

        return ApiResponse.successResponse(mapItemToResponse(save));
    }


    private MaterialResponse mapItemToResponse(Material material) {
        MaterialResponse materialResponse = new MaterialResponse();
        materialResponse.setId(material.getId());
        materialResponse.setMaterialTypeId(material.getMaterialType().getId());
        materialResponse.setQuantity(material.getQuantity());
        materialResponse.setMaterialCategoryId(material.getMaterialCategory().getId());
        materialResponse.setDescription(material.getDescription());
        if (material.getUser() != null) {
            materialResponse.setUserId(material.getUser().getId());
        }

        materialResponse.setCreatedAt(material.getCreatedAt());
        materialResponse.setUpdatedAt(material.getUpdatedAt());
        return materialResponse;
    }
}
