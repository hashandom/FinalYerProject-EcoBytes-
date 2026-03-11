package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.dto.StorageLocationRequestDto;
import com.example.EcoBytes.dto.StorageLocationResponseDto;
import com.example.EcoBytes.service.StorageLocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage-locations")
@RequiredArgsConstructor
public class StorageLocationController {

    private final StorageLocationService service;


    @PostMapping
    public ResponseEntity<ApiResponse<StorageLocationResponseDto>> create(
            @Valid @RequestBody StorageLocationRequestDto dto) {
        StorageLocationResponseDto responseDto = service.create(dto);
        ApiResponse<StorageLocationResponseDto> response = ApiResponse.<StorageLocationResponseDto>builder()
                .success(true)
                .message("Storage location created successfully")
                .data(responseDto)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StorageLocationResponseDto>> getById(@PathVariable String id) {
        StorageLocationResponseDto responseDto = service.getById(id);
        ApiResponse<StorageLocationResponseDto> response = ApiResponse.<StorageLocationResponseDto>builder()
                .success(true)
                .message("Storage location retrieved successfully")
                .data(responseDto)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<StorageLocationResponseDto>>> getAll() {
        List<StorageLocationResponseDto> locations = service.getAll();
        ApiResponse<List<StorageLocationResponseDto>> response = ApiResponse.<List<StorageLocationResponseDto>>builder()
                .success(true)
                .message("Storage locations retrieved successfully")
                .data(locations)
                .build();
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StorageLocationResponseDto>> update(
            @PathVariable String id,
            @Valid @RequestBody StorageLocationRequestDto dto) {
        StorageLocationResponseDto updated = service.update(id, dto);
        ApiResponse<StorageLocationResponseDto> response = ApiResponse.<StorageLocationResponseDto>builder()
                .success(true)
                .message("Storage location updated successfully")
                .data(updated)
                .build();
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {
        service.delete(id);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Storage location deleted successfully")
                .data(id)
                .build();
        return ResponseEntity.ok(response);
    }
}
