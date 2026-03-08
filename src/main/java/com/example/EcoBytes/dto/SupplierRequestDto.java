package com.example.EcoBytes.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SupplierRequestDto {
    @NotBlank(message = "Supplier name is required")
    private String name;

    @NotBlank(message = "Contact is required")
    private String contact;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Double rating;

    @Positive(message = "Average delivery time must be positive")
    private Integer avgDeliveryTime;

    @Positive(message = "Quality score must be positive")
    private Double qualityScore;

    @Positive(message = "Cost must be positive")
    private Double cost;
}
