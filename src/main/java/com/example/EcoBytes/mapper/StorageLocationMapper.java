package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.StorageLocationRequestDto;
import com.example.EcoBytes.dto.StorageLocationResponseDto;
import com.example.EcoBytes.entity.StorageLocation;

public class StorageLocationMapper {

    public static StorageLocation toEntity(StorageLocationRequestDto dto) {

        StorageLocation location = new StorageLocation();
        location.setName(dto.getName());
        location.setType(dto.getType());

        return location;
    }

    public static StorageLocationResponseDto toResponseDto(StorageLocation location) {

        return StorageLocationResponseDto.builder()
                .locationId(location.getLocationId())
                .name(location.getName())
                .type(location.getType())
                .build();
    }
}
