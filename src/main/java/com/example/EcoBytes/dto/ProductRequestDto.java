package com.example.EcoBytes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    private Double unitPrice;

    @NotNull
    private Integer reorderLevel;
}
