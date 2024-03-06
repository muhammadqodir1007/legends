package com.fazo.esm.controller;

import com.fazo.esm.entity.MaterialCategory;
import com.fazo.esm.payload.dto.MaterialCategoryDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.service.MaterialCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/material-categories")
@AllArgsConstructor
@Slf4j
public class MatCatController {

    private final MaterialCategoryService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialCategory>>> getAll() {
        try {
            ApiResponse<List<MaterialCategory>> response = service.getAll();
            log.info("Get all material categories request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing get all material categories request", e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialCategory>> getById(@PathVariable int id) {
        try {
            ApiResponse<MaterialCategory> response = service.getById(id);
            log.info("Get material category by ID request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing get material category by ID request", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MaterialCategory>> create(@RequestBody MaterialCategoryDto dto) {
        try {
            ApiResponse<MaterialCategory> response = service.create(dto);
            log.info("Create material category request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing create material category request", e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialCategory>> update(@PathVariable int id, @RequestBody MaterialCategoryDto dto) {
        try {
            ApiResponse<MaterialCategory> response = service.update(id, dto);
            log.info("Update material category request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing update material category request", e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable int id) {
        try {
            ApiResponse<String> response = service.delete(id);
            log.info("Delete material category request successful");
            return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
        } catch (Exception e) {
            log.error("Error while processing delete material category request", e);
            throw e;
        }
    }
}
