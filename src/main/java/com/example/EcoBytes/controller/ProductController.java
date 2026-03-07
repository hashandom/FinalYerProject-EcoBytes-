package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.ApiResponse;
import com.example.EcoBytes.entity.Product;
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
    public ResponseEntity<ApiResponse<Product>> create(@Valid @RequestBody Product product) {

        Product savedProduct = productService.save(product);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product created successfully")
                .data(savedProduct)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getById(@PathVariable String id) {

        Product product = productService.getById(id);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(product)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> update(
            @PathVariable String id,
            @Valid @RequestBody Product product) {

        Product updatedProduct = productService.update(id, product);

        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product updated successfully")
                .data(updatedProduct)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> delete(@PathVariable String id) {

        // 1. Get product before deleting
        Product existingProduct = productService.getById(id);

        // 2. Delete it
        productService.delete(id);

        // 3. Return deleted product in response
        ApiResponse<Product> response = ApiResponse.<Product>builder()
                .success(true)
                .message("Product deleted successfully")
                .data(existingProduct)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}