package com.example.EcoBytes.repository;

import com.example.EcoBytes.entity.StorageLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StorageLocationRepository  extends JpaRepository<StorageLocation, String> {
    Optional<StorageLocation> findTopByOrderByLocationIdDesc();
}
