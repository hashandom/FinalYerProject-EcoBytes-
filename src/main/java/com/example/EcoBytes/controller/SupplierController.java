package com.example.EcoBytes.controller;

import com.example.EcoBytes.response.ApiResponse;
import com.example.EcoBytes.dto.SupplierRequestDto;
import com.example.EcoBytes.dto.SupplierResponseDto;
import com.example.EcoBytes.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    // CREATE
    @PostMapping
    public ApiResponse<SupplierResponseDto> createSupplier(@Valid @RequestBody SupplierRequestDto dto) {
        SupplierResponseDto response = supplierService.createSupplier(dto);
        return new ApiResponse<>(
                true,                             // success
                "Supplier created successfully",  // message
                response,                         // data
                LocalDateTime.now()
        );
    }

    // GET ALL
    @GetMapping
    public ApiResponse<List<SupplierResponseDto>> getAllSuppliers() {

        List<SupplierResponseDto> suppliers = supplierService.getAllSuppliers();

        return new ApiResponse<>(
                true,
                "Supplier list",
                suppliers,
                LocalDateTime.now()

        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ApiResponse<SupplierResponseDto> getSupplier(@PathVariable String id) {

        SupplierResponseDto response = supplierService.getSupplierById(id);

        return new ApiResponse<>(
                true,
                "Supplier found",
                response,
                LocalDateTime.now()

        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApiResponse<SupplierResponseDto> updateSupplier(
            @PathVariable String id,
            @Valid @RequestBody SupplierRequestDto dto) {

        SupplierResponseDto response = supplierService.updateSupplier(id, dto);

        return new ApiResponse<>(
                true,
                "Supplier updated successfully",
                response,
                LocalDateTime.now()
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSupplier(@PathVariable String id) {

        supplierService.deleteSupplier(id);

        return new ApiResponse<>(
                true,
                "Supplier deleted successfully",
                null,
                LocalDateTime.now()
        );
    }
}
