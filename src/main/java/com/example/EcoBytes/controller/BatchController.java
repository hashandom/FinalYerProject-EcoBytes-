package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.repository.BatchRepository;
import com.example.EcoBytes.service.BatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/batches")
@RequiredArgsConstructor

public class BatchController {

    private final BatchService batchService;

    @GetMapping("/fefo/{productId}")
    public ApiResponse<List<Batch>> getFEFO(@PathVariable Long productId) {

        List<Batch> batches = batchService.getFEFOBatches(productId);

        return ApiResponse.<List<Batch>>builder()
                .success(true)
                .message("FEFO batch list retrieved successfully")
                .data(batches)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
