package com.example.EcoBytes.controller;

import com.example.EcoBytes.response.ApiResponse;
import com.example.EcoBytes.dto.ProductRequestDto;
import com.example.EcoBytes.dto.ProductResponseDto;
import com.example.EcoBytes.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> create(
            @Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto savedProduct = productService.save(dto);

        ApiResponse<ProductResponseDto> response = ApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product created successfully")
                .data(savedProduct)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAll() {

        List<ProductResponseDto> products = productService.getAll();

        ApiResponse<List<ProductResponseDto>> response = ApiResponse.<List<ProductResponseDto>>builder()
                .success(true)
                .message("Product list retrieved")
                .data(products)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getById(@PathVariable String id) {

        ProductResponseDto product = productService.getById(id);

        ApiResponse<ProductResponseDto> response = ApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> update(
            @PathVariable String id,
            @Valid @RequestBody ProductRequestDto dto) {

        ProductResponseDto updatedProduct = productService.update(id, dto);

        ApiResponse<ProductResponseDto> response = ApiResponse.<ProductResponseDto>builder()
                .success(true)
                .message("Product updated successfully")
                .data(updatedProduct)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {

        productService.delete(id);

        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message("Product deleted successfully")
                .data("Deleted product id: " + id)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}