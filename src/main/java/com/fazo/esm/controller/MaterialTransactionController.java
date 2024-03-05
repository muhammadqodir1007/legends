package com.fazo.esm.controller;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.MaterialTransactionRes;
import com.fazo.esm.service.MaterialTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/material-transactions")
@AllArgsConstructor
@Slf4j
public class MaterialTransactionController {

    private final MaterialTransactionService materialTransactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<MaterialTransactionRes>>> getAll() {
        log.info("Fetching all material transactions");
        return ResponseEntity.ok(materialTransactionService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<MaterialTransactionRes>>> getByUserId(@PathVariable int userId) {
        log.info("Fetching material transactions for user with ID: {}", userId);
        return ResponseEntity.ok(materialTransactionService.getByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MaterialTransactionRes>> getById(@PathVariable int id) {
        log.info("Fetching material transaction with ID: {}", id);
        return ResponseEntity.ok(materialTransactionService.getById(id));
    }
}
