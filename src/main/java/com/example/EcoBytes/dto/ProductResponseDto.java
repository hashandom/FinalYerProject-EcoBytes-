package com.example.EcoBytes.dto;

import lombok.Data;

@Data
public class ProductResponseDto {

    private String productId;
    private String name;
    private String category;
    private Double unitPrice;
    private Integer reorderLevel;
}
