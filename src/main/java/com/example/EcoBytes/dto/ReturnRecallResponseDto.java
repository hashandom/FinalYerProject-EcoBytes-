package com.example.EcoBytes.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReturnRecallResponseDto {

    private String recallId;
    private String reason;
    private LocalDate date;
    private String batchId;
}
