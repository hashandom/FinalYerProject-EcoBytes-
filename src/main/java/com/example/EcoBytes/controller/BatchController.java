package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.dto.BatchRequestDto;
import com.example.EcoBytes.dto.BatchResponseDto;
import com.example.EcoBytes.service.BatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor
public class BatchController {

    private final BatchService batchService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<BatchResponseDto>> createBatch(
            @Valid @RequestBody BatchRequestDto dto) {

        BatchResponseDto saved = batchService.createBatch(dto);

        ApiResponse<BatchResponseDto> response = ApiResponse.<BatchResponseDto>builder()
                .success(true)
                .message("Batch created successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<BatchResponseDto>>> getAllBatches() {

        List<BatchResponseDto> batches = batchService.getAllBatches();

        ApiResponse<List<BatchResponseDto>> response = ApiResponse.<List<BatchResponseDto>>builder()
                .success(true)
                .message("Batch list retrieved")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // GET BY ID
    @GetMapping("/{batchCode}")
    public ResponseEntity<ApiResponse<BatchResponseDto>> getBatchById(@PathVariable String batchCode) {

        BatchResponseDto batch = batchService.getBatchById(batchCode);

        ApiResponse<BatchResponseDto> response = ApiResponse.<BatchResponseDto>builder()
                .success(true)
                .message("Batch found")
                .data(batch)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{batchCode}")
    public ResponseEntity<ApiResponse<BatchResponseDto>> updateBatch(
            @PathVariable String batchCode,
            @Valid @RequestBody BatchRequestDto dto) {

        BatchResponseDto updated = batchService.updateBatch(batchCode, dto);

        ApiResponse<BatchResponseDto> response = ApiResponse.<BatchResponseDto>builder()
                .success(true)
                .message("Batch updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{batchCode}")
    public ResponseEntity<ApiResponse<String>> deleteBatch(@PathVariable String batchCode) {

        batchService.deleteBatch(batchCode);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Batch deleted successfully")
                .data("Deleted BatchCode : " + batchCode)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // FEFO - First Expired First Out
    @GetMapping("/fefo/{productId}")
    public ResponseEntity<ApiResponse<List<BatchResponseDto>>> getFEFO(@PathVariable String productId) {

        List<BatchResponseDto> batches = batchService.getFEFOBatches(productId);

        ApiResponse<List<BatchResponseDto>> response = ApiResponse.<List<BatchResponseDto>>builder()
                .success(true)
                .message("FEFO batch list retrieved")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    // LOW STOCK ALERT SYSTEM
    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<BatchResponseDto>>> getLowStockBatches() {

        List<BatchResponseDto> lowStockBatches = batchService.getLowStockBatches();

        ApiResponse<List<BatchResponseDto>> response = ApiResponse.<List<BatchResponseDto>>builder()
                .success(true)
                .message("Low stock batches retrieved successfully")
                .data(lowStockBatches)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}