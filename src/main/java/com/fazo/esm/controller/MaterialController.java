package com.fazo.esm.controller;

import com.fazo.esm.payload.dto.MaterialDto;
import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.MaterialResponse;
import com.fazo.esm.service.MaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final Logger logger = LoggerFactory.getLogger(MaterialController.class);

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> getAll() {
        logger.info("Received request to get all materials");
        ApiResponse<List<MaterialResponse>> response = materialService.getAll();
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialResponse>> getById(@PathVariable int id) {
        logger.info("Received request to get material by ID: {}", id);
        ApiResponse<MaterialResponse> response = materialService.getById(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MaterialResponse>> create(@RequestBody MaterialDto materialDto) {
        logger.info("Received request to create a new material: {}", materialDto);
        ApiResponse<MaterialResponse> response = materialService.insert(materialDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> getAllByCategoryId(@PathVariable int id) {
        logger.info("Received request to get materials by category ID: {}", id);
        ApiResponse<List<MaterialResponse>> response = materialService.getAllByCategoryId(id);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MaterialResponse>>> search(@RequestParam String name) {
        logger.info("Received request to search materials by name: {}", name);
        ApiResponse<List<MaterialResponse>> response = materialService.search(name);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }

    @PatchMapping
    public ResponseEntity<ApiResponse<MaterialResponse>> delete(@RequestBody MaterialDto materialDto) {
        logger.info("Received request to delete material: {}", materialDto);
        ApiResponse<MaterialResponse> response = materialService.delete(materialDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 400).body(response);
    }
}
