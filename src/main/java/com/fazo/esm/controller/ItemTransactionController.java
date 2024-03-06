package com.fazo.esm.controller;

import com.fazo.esm.payload.response.ApiResponse;
import com.fazo.esm.payload.response.ItemTransactionRes;
import com.fazo.esm.service.ItemTransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
@Slf4j
public class ItemTransactionController {

    private final ItemTransactionService itemTransactionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ItemTransactionRes>>> getAll() {
        log.info("Fetching all item transactions");
        ApiResponse<List<ItemTransactionRes>> response = itemTransactionService.getAll();
        log.info("Get all item transactions request completed");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<ApiResponse<List<ItemTransactionRes>>> getAllByAdmin(@PathVariable int adminId) {
        log.info("Fetching item transactions by admin ID: {}", adminId);
        ApiResponse<List<ItemTransactionRes>> response = itemTransactionService.getByAdminId(adminId);
        log.info("Get item transactions by admin ID request completed");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ItemTransactionRes>> getById(@PathVariable int id) {
        log.info("Fetching item transaction by ID: {}", id);
        ApiResponse<ItemTransactionRes> response = itemTransactionService.getById(id);
        log.info("Get item transaction by ID request completed");
        return ResponseEntity.ok(response);
    }
}
