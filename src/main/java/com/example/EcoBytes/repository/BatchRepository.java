package com.example.EcoBytes.repository;

import com.example.EcoBytes.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findByProductProductIdOrderByExpiryDateAsc(Long productId);
}
