package com.fazo.esm.controller;

import com.fazo.esm.entity.MaterialType;
import com.fazo.esm.payload.dto.MaterialTypeDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.service.MaterialTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/material-types")
@AllArgsConstructor
@Slf4j
public class MaterialTypeController {

    private final MaterialTypeService materialTypeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialType>>> getAll() {
        log.info("Fetching all material types");
        ApiResponse<List<MaterialType>> response = materialTypeService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialType>> getById(@PathVariable int id) {
        log.info("Fetching material type with ID: {}", id);
        ApiResponse<MaterialType> response = materialTypeService.getById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MaterialType>> create(@RequestBody MaterialTypeDto materialTypeDto) {
        log.info("Creating a new material type: {}", materialTypeDto);
        ApiResponse<MaterialType> response = materialTypeService.create(materialTypeDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 400).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialType>> update(@PathVariable int id, @RequestBody MaterialTypeDto materialTypeDto) {
        log.info("Updating material type with ID {}: {}", id, materialTypeDto);
        ApiResponse<MaterialType> response = materialTypeService.update(id, materialTypeDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable int id) {
        log.info("Deleting material type with ID: {}", id);
        ApiResponse<String> response = materialTypeService.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
