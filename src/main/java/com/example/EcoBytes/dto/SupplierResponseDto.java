package com.example.EcoBytes.dto;


import lombok.Data;

@Data
public class SupplierResponseDto {

    private String supplierId;
    private String name;
    private String contact;
    private Double rating;
    private Integer avgDeliveryTime;
    private Double qualityScore;
    private Double cost;
}
