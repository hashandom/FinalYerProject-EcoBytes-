package com.example.EcoBytes.repository;

import com.example.EcoBytes.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
    Supplier findTopByOrderBySupplierIdDesc();
}
