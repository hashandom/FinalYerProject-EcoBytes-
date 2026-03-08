package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.SpoilageResponseDto;
import com.example.EcoBytes.entity.SpoilageLog;

public class SpoilageMapper {

    public static SpoilageResponseDto toDTO(SpoilageLog log){

        return SpoilageResponseDto.builder()
                .spoilageId(log.getSpoilageId())
                .batchId(log.getBatch().getBatchCode())
                .reason(log.getReason())
                .quantity(log.getQuantity())
                .date(log.getDate())
                .build();
    }
}
