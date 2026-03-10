package com.example.EcoBytes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SpoilageRequestDto {

    @NotBlank(message = "Batch ID is required")
    private String batchId;

    @NotBlank(message = "Reason is required")
    private String reason;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "data is required")
    private LocalDate date;
}
