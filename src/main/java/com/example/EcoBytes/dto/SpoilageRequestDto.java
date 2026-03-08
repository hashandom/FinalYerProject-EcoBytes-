package com.example.EcoBytes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpoilageRequestDto {

    @NotBlank
    private String batchId;

    @NotBlank
    private String reason;

    @Min(1)
    private Integer quantity;
}
