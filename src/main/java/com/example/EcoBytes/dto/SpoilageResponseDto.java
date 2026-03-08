package com.example.EcoBytes.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SpoilageResponseDto {

    private String spoilageId;
    private String batchId;
    private String reason;
    private Integer quantity;
    private LocalDate date;
}
