package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.SupplierRequestDto;
import com.example.EcoBytes.dto.SupplierResponseDto;
import com.example.EcoBytes.entity.Supplier;

public class SupplierMapper {
    public static Supplier toEntity(SupplierRequestDto dto) {

        Supplier supplier = new Supplier();

        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setRating(dto.getRating());
        supplier.setAvgDeliveryTime(dto.getAvgDeliveryTime());
        supplier.setQualityScore(dto.getQualityScore());
        supplier.setCost(dto.getCost());

        return supplier;
    }

    public static SupplierResponseDto toDTO(Supplier supplier) {

        SupplierResponseDto dto = new SupplierResponseDto();

        dto.setSupplierId(supplier.getSupplierId());
        dto.setName(supplier.getName());
        dto.setContact(supplier.getContact());
        dto.setRating(supplier.getRating());
        dto.setAvgDeliveryTime(supplier.getAvgDeliveryTime());
        dto.setQualityScore(supplier.getQualityScore());
        dto.setCost(supplier.getCost());

        return dto;
    }
}
