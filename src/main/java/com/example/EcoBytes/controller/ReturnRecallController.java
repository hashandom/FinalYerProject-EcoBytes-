package com.example.EcoBytes.controller;

import com.example.EcoBytes.response.ApiResponse;
import com.example.EcoBytes.dto.ReturnRecallRequestDto;
import com.example.EcoBytes.dto.ReturnRecallResponseDto;
import com.example.EcoBytes.service.ReturnRecallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/recall")
@RequiredArgsConstructor
public class ReturnRecallController {
    private final ReturnRecallService returnRecallService;
    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<ReturnRecallResponseDto>> create(@Valid @RequestBody ReturnRecallRequestDto dto) {
        ReturnRecallResponseDto savedRecall = returnRecallService.createRecall(dto);
        ApiResponse<ReturnRecallResponseDto> response = ApiResponse.<ReturnRecallResponseDto>builder()
                .success(true)
                .message("Return/Recall created successfully")
                .data(savedRecall)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReturnRecallResponseDto>>> getAll() {
        List<ReturnRecallResponseDto> recalls = returnRecallService.getAllRecalls();
        ApiResponse<List<ReturnRecallResponseDto>> response = ApiResponse.<List<ReturnRecallResponseDto>>builder()
                .success(true)
                .message("Recall list retrieved")
                .data(recalls)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReturnRecallResponseDto>> getById(@PathVariable String id) {
        ReturnRecallResponseDto recall = returnRecallService.getRecallById(id);
        ApiResponse<ReturnRecallResponseDto> response = ApiResponse.<ReturnRecallResponseDto>builder()
                .success(true)
                .message("Recall fetched successfully")
                .data(recall)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


    // GET BY BATCH
    @GetMapping("/batch/{batchId}")
    public ResponseEntity<ApiResponse<List<ReturnRecallResponseDto>>> getByBatch(@PathVariable String batchId) {
        List<ReturnRecallResponseDto> recalls = returnRecallService.getRecallsByBatch(batchId);
        ApiResponse<List<ReturnRecallResponseDto>> response = ApiResponse.<List<ReturnRecallResponseDto>>builder()
                .success(true)
                .message("Recall list for batch retrieved")
                .data(recalls)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReturnRecallResponseDto>> update(
            @PathVariable String id,
            @Valid @RequestBody ReturnRecallRequestDto dto) {
        ReturnRecallResponseDto updatedRecall = returnRecallService.updateRecall(id, dto);
        ApiResponse<ReturnRecallResponseDto> response = ApiResponse.<ReturnRecallResponseDto>builder()
                .success(true)
                .message("Recall updated successfully")
                .data(updatedRecall)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {
        returnRecallService.deleteRecall(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Recall deleted successfully")
                .data("Deleted recall id: " + id)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
