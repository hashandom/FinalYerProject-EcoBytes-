package com.example.EcoBytes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorageLocationResponseDto {
    private String locationId;
    private String name;
    private String type;
}
