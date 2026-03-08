package com.example.EcoBytes.dto;

import lombok.Data;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BatchResponseDto {

    private String batchCode;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private Integer quantity;
    private String productId;
    private String productName;
    private String supplierId;
}
