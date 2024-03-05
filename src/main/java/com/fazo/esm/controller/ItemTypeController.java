package com.fazo.esm.controller;

import com.fazo.esm.payload.dto.ItemTypeDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.entity.ItemType;
import com.fazo.esm.service.ItemTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-types")
@RequiredArgsConstructor
@Slf4j
public class ItemTypeController {

    private final ItemTypeService itemTypeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemType>>> getAll() {
        try {
            ApiResponse<List<ItemType>> response = ApiResponse.successResponse(itemTypeService.getAllItemTypes());
            log.info("Get all item types request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing get all item types request", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemType>> getById(@PathVariable int id) {
        try {
            ApiResponse<ItemType> response = ApiResponse.successResponse(itemTypeService.getItemTypeById(id));
            log.info("Get item type by ID request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing get item type by ID request", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ItemType>> create(@RequestBody ItemTypeDto itemTypeDto) {
        try {
            ItemType createdItemType = itemTypeService.createItemType(itemTypeDto);
            ApiResponse<ItemType> response = ApiResponse.successResponse(createdItemType, "ItemType successfully created");
            log.info("Create item type request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing create item type request", e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemType>> update(@PathVariable int id, @RequestBody ItemTypeDto itemTypeDto) {
        try {
            ItemType updatedItemType = itemTypeService.updateItemType(id, itemTypeDto);
            ApiResponse<ItemType> response = ApiResponse.successResponse(updatedItemType, "ItemType successfully updated");
            log.info("Update item type request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing update item type request", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable int id) {
        try {
            itemTypeService.deleteItemType(id);
            log.info("Delete item type request successful");
            return ResponseEntity.ok(ApiResponse.successResponse(null, "ItemType successfully deleted"));
        } catch (Exception e) {
            log.error("Error while processing delete item type request", e);
            throw e;
        }
    }
}
