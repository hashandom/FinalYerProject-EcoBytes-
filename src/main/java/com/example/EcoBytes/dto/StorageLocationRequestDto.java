package com.example.EcoBytes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StorageLocationRequestDto {

    @NotBlank(message = "Storage location name is required")
    private String name;

    @NotBlank(message = "Storage type is  required")
    private String type;
}
