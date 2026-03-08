package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.BatchRequestDto;
import com.example.EcoBytes.dto.BatchResponseDto;
import com.example.EcoBytes.entity.Batch;

public class BatchMapper {
    // ENTITY → RESPONSE DTO

    public static BatchResponseDto toDto(Batch batch) {
        if (batch == null) return null;

        BatchResponseDto dto = new BatchResponseDto();

        dto.setBatchCode(batch.getBatchCode());
        dto.setManufactureDate(batch.getManufactureDate());
        dto.setExpiryDate(batch.getExpiryDate());
        dto.setQuantity(batch.getQuantity());

        if (batch.getProduct() != null) {
            dto.setProductId(String.valueOf(batch.getProduct().getProductId()));
            dto.setProductName(batch.getProduct().getName());
        }

        if (batch.getSupplier() != null) {
            dto.setSupplierId(batch.getSupplier().getSupplierId());
        }

        return dto;
    }

    // REQUEST DTO → ENTITY
    public static Batch toEntity(BatchRequestDto dto) {
        if (dto == null) return null;

        Batch batch = new Batch();

        // batchCode is usually generated in service
        batch.setBatchCode(dto.getBatchCode());
        batch.setManufactureDate(dto.getManufactureDate());
        batch.setExpiryDate(dto.getExpiryDate());
        batch.setQuantity(dto.getQuantity());

        return batch;
    }

    // UPDATE EXISTING ENTITY WITH REQUEST DTO
    public static void updateEntity(Batch batch, BatchRequestDto dto) {
        if (batch == null || dto == null) return;

        batch.setManufactureDate(dto.getManufactureDate());
        batch.setExpiryDate(dto.getExpiryDate());
        batch.setQuantity(dto.getQuantity());
        // batchCode usually not updated
    }
}
