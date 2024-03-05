package com.fazo.esm.controller;

import com.fazo.esm.entity.Category;
import com.fazo.esm.payload.dto.CategoryDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAll() {
        log.info("Fetching all categories");
        ApiResponse<List<Category>> response = categoryService.getAllCategories();
        log.info("Get all categories request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getById(@PathVariable int id) {
        log.info("Fetching category by ID: {}", id);
        ApiResponse<Category> response = categoryService.getCategoryById(id);
        log.info("Get category by ID request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody CategoryDto categoryDto) {
        log.info("Creating a new category: {}", categoryDto);
        ApiResponse<Category> response = categoryService.createCategory(categoryDto);
        log.info("Create category request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        log.info("Updating category with ID {}: {}", id, categoryDto);
        ApiResponse<Category> response = categoryService.updateCategory(id, categoryDto);
        log.info("Update category request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable int id) {
        log.info("Deleting category with ID: {}", id);
        ApiResponse<Void> response = categoryService.deleteCategory(id);
        log.info("Delete category request completed");
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
