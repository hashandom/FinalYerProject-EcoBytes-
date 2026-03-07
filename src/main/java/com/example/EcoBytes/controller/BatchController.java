package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.entity.Batch;
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
    public ApiResponse<Batch> createBatch(@Valid @RequestBody Batch batch) {

        Batch saved = batchService.createBatch(batch);

        return ApiResponse.<Batch>builder()
                .success(true)
                .message("Batch created successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // GET ALL
    @GetMapping
    public ApiResponse<List<Batch>> getAllBatches() {

        List<Batch> batches = batchService.getAllBatches();

        return ApiResponse.<List<Batch>>builder()
                .success(true)
                .message("Batch list retrieved")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ApiResponse<Batch> getBatchById(@PathVariable String id) {

        Batch batch = batchService.getBatchById(id);

        return ApiResponse.<Batch>builder()
                .success(true)
                .message("Batch found")
                .data(batch)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApiResponse<Batch> updateBatch(@PathVariable String id,
                                          @RequestBody Batch batch) {

        Batch updated = batchService.updateBatch(id, batch);

        return ApiResponse.<Batch>builder()
                .success(true)
                .message("Batch updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBatch(@PathVariable String id) {

        batchService.deleteBatch(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Batch deleted successfully")
                .data("Deleted ID : " + id)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // FEFO
    @GetMapping("/fefo/{productId}")
    public ApiResponse<List<Batch>> getFEFO(@PathVariable String productId) {

        List<Batch> batches = batchService.getFEFOBatches(productId);

        return ApiResponse.<List<Batch>>builder()
                .success(true)
                .message("FEFO batch list retrieved")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();
    }

    //LOW STOCK ALERT SYSTEM
    @GetMapping("/low-stock")
    public ResponseEntity<ApiResponse<List<Batch>>> getLowStockBatches() {

        List<Batch> lowStockBatches = batchService.getLowStock();

        ApiResponse<List<Batch>> response = new ApiResponse<>(
                true,
                "Low stock batches retrieved successfully",
                lowStockBatches,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}