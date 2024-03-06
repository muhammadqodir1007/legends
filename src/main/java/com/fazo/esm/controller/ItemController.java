package com.fazo.esm.controller;

import com.fazo.esm.payload.dto.ItemDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.ItemResponse;
import com.fazo.esm.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> search(@RequestParam String name) {
        log.info("Searching for items with name: {}", name);
        ApiResponse<List<ItemResponse>> response = itemService.search(name);
        log.info("Search request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getAll() {
        log.info("Fetching all items");
        ApiResponse<List<ItemResponse>> response = itemService.getAll();
        log.info("Get all items request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemResponse>> getById(@PathVariable int id) {
        log.info("Fetching item by ID: {}", id);
        ApiResponse<ItemResponse> response = itemService.getById(id);
        log.info("Get item by ID request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody ItemDto itemDto) {
        log.info("Creating a new item: {}", itemDto);
        ApiResponse<?> response = itemService.insert(itemDto);
        log.info("Create item request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<ItemResponse>>> getItemsByCategory(@PathVariable int categoryId) {
        log.info("Fetching items by category ID: {}", categoryId);
        ApiResponse<List<ItemResponse>> response = itemService.getAllByCategory(categoryId);
        log.info("Get items by category ID request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> delete(@RequestBody ItemDto itemDto) {
        log.info("Deleting item: {}", itemDto);
        ApiResponse<?> response = itemService.delete(itemDto);
        log.info("Delete item request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
