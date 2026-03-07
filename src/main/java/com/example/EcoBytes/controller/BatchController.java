package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.service.BatchService;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<Batch> createBatch(@RequestBody Batch batch) {

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
    public ApiResponse<Batch> getBatchById(@PathVariable Long id) {

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
    public ApiResponse<Batch> updateBatch(@PathVariable Long id,
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
    public ApiResponse<String> deleteBatch(@PathVariable Long id) {

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
    public ApiResponse<List<Batch>> getFEFO(@PathVariable Long productId) {

        List<Batch> batches = batchService.getFEFOBatches(productId);

        return ApiResponse.<List<Batch>>builder()
                .success(true)
                .message("FEFO batch list retrieved")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();
    }
}