package com.example.EcoBytes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReturnRecallRequestDto {

    @NotBlank
    private String reason;

    @NotNull
    private LocalDate date;

    @NotBlank
    private String batchId;
}
