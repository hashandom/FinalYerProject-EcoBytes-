package com.example.EcoBytes.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchRequestDto {
    private String batchCode;

    @NotNull(message = "Manufacture date is required")
    private LocalDate manufactureDate;

    @NotNull(message = "Expiry date is required")
    private LocalDate expiryDate;

    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @NotNull(message = "Product ID is required")
    private String productId;

    private String supplierId;
}
