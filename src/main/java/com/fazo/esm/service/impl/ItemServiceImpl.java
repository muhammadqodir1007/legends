package com.fazo.esm.service.impl;

import com.fazo.esm.entity.Category;
import com.fazo.esm.entity.Item;
import com.fazo.esm.entity.ItemType;
import com.fazo.esm.entity.User;
import com.fazo.esm.exception.RestException;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.dto.ItemDto;
import com.fazo.esm.payload.response.ItemResponse;
import com.fazo.esm.repository.CategoryRepository;
import com.fazo.esm.repository.ItemRepository;
import com.fazo.esm.repository.ItemTypeRepository;
import com.fazo.esm.repository.UserRepository;
import com.fazo.esm.service.ItemService;
import com.fazo.esm.service.ItemTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemTypeRepository itemTypeRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemTransactionService itemTransactionService;
    private final UserRepository userRepository;

    @Override
    public ApiResponse<List<ItemResponse>> search(String name) {
        List<Item> items = itemRepository.findByItemTypeNameContaining(name);
        List<ItemResponse> responses = items.stream()
                .map(this::mapItemToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(responses);
    }

    @Override
    public ApiResponse<List<ItemResponse>> getAll() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> list = items.stream()
                .map(this::mapItemToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(list);
    }

    @Override
    public ApiResponse<ItemResponse> getById(int id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> RestException.restThrow("Product not found"));
        return ApiResponse.successResponse(mapItemToResponse(item));
    }

    @Override
    public ApiResponse<ItemResponse> insert(ItemDto itemDto) {
        Category category = categoryRepository.findById(itemDto.getCategoryId())
                .orElseThrow(() -> RestException.restThrow("Category not found"));
        User user = userRepository.findById(itemDto.getAdminId())
                .orElseThrow(() -> RestException.restThrow("User not found"));
        ItemType itemType = itemTypeRepository.findById(itemDto.getItemType())
                .orElseThrow(() -> RestException.restThrow("Item Type not found"));

        boolean isExist = itemRepository.existsByItemType(itemType);
        Item item;
        if (isExist) {
            item = itemRepository.findByItemType(itemType)
                    .orElseThrow(() -> RestException.restThrow("Item not found"));
            item.setQuantity(item.getQuantity() + itemDto.getQuantity());
            item.setUpdatedAt(LocalDateTime.now());
        } else {
            item = new Item();
            item.setItemType(itemType);
            item.setCategory(category);
            item.setDescription(itemDto.getDescription());
            item.setQuantity(itemDto.getQuantity());
            item.setCreatedAt(LocalDateTime.now());
        }
        item.setUser(user);
        Item save = itemRepository.save(item);
        ItemResponse itemResponse = mapItemToResponse(save);
        itemTransactionService.add(itemDto, "added");
        return ApiResponse.successResponse(itemResponse, "Material successfully saved");
    }

    @Override
    public ApiResponse<List<ItemResponse>> getAllByCategory(int categoryId) {
        List<Item> itemsByCategoryId = itemRepository.findAllByCategoryId(categoryId);
        List<ItemResponse> itemResponses = itemsByCategoryId.stream()
                .map(this::mapItemToResponse)
                .collect(Collectors.toList());
        return ApiResponse.successResponse(itemResponses);
    }

    @Override
    public ApiResponse<ItemResponse> delete(ItemDto itemDto) {
        ItemType itemType1 = itemTypeRepository.findById(itemDto.getItemType())
                .orElseThrow(() -> RestException.restThrow("Item type not found"));
        User user = userRepository.findById(itemDto.getAdminId())
                .orElseThrow(() -> RestException.restThrow("User not found"));
        boolean exists = itemRepository.existsByItemType(itemType1);
        Item save;
        if (exists) {
            Item item = itemRepository.findByItemType(itemType1)
                    .orElseThrow(() -> RestException.restThrow("Item not found"));
            if (item.getQuantity() < itemDto.getQuantity())
                throw RestException.restThrow("There is not enough product");
            item.setQuantity(item.getQuantity() - itemDto.getQuantity());
            item.setUser(user);
            save = itemRepository.save(item);
        } else {
            throw RestException.restThrow("Item not found");
        }
        itemTransactionService.delete(itemDto, "subtracted");
        return ApiResponse.successResponse(mapItemToResponse(save));
    }

    private ItemResponse mapItemToResponse(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.setItemType(item.getItemType().getId());
        itemResponse.setQuantity(item.getQuantity());
        itemResponse.setCategoryId(item.getCategory().getId());
        itemResponse.setUserId(item.getUser() != null ? item.getUser().getId() : 0);
        itemResponse.setId(item.getId());
        itemResponse.setDescription(item.getDescription());
        itemResponse.setCreatedAt(item.getCreatedAt());
        itemResponse.setUpdatedAt(item.getUpdatedAt());
        return itemResponse;
    }
}
