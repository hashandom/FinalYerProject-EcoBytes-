package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.StorageLocationRequestDto;
import com.example.EcoBytes.dto.StorageLocationResponseDto;
import com.example.EcoBytes.entity.StorageLocation;
import com.example.EcoBytes.mapper.StorageLocationMapper;
import com.example.EcoBytes.repository.StorageLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StorageLocationService {

    private final StorageLocationRepository repository;

    private String generateLocationId() {
        Optional<StorageLocation> lastLocation = repository.findTopByOrderByLocationIdDesc();
        if (lastLocation.isPresent()) {
            String lastId = lastLocation.get().getLocationId();
            int number = Integer.parseInt(lastId.split("-")[1]);
            number++;
            return String.format("Location-%03d", number);
        }
        return "Location-001";
    }


    public StorageLocationResponseDto create(StorageLocationRequestDto dto) {
        String newId = generateLocationId();
        StorageLocation location = StorageLocationMapper.toEntity(dto);
        location.setLocationId(newId);
        StorageLocation saved = repository.save(location);
        return StorageLocationMapper.toResponseDto(saved);
    }


    public List<StorageLocationResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(StorageLocationMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    public StorageLocationResponseDto getById(String id) {
        StorageLocation location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return StorageLocationMapper.toResponseDto(location);
    }


    public StorageLocationResponseDto update(String id, StorageLocationRequestDto dto) {
        StorageLocation location = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        location.setName(dto.getName());
        location.setType(dto.getType());
        StorageLocation updated = repository.save(location);
        return StorageLocationMapper.toResponseDto(updated);
    }


    public void delete(String id) {
        repository.deleteById(id);
    }

}
