package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.ReturnRecallRequestDto;
import com.example.EcoBytes.dto.ReturnRecallResponseDto;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.entity.ReturnRecall;

public class ReturnRecallMapper {
    // Convert Request DTO -> Entity
    public static ReturnRecall toEntity(ReturnRecallRequestDto dto, Batch batch) {

        ReturnRecall recall = new ReturnRecall();

        recall.setReason(dto.getReason());
        recall.setDate(dto.getDate());
        recall.setBatch(batch);

        return recall;
    }

    // Convert Entity -> Response DTO
    public static ReturnRecallResponseDto toDTO(ReturnRecall recall) {
        return ReturnRecallResponseDto.builder()
                .recallId(recall.getRecallId())
                .reason(recall.getReason())
                .date(recall.getDate())
                .batchId(recall.getBatch().getBatchCode())
                .build();
    }
}
