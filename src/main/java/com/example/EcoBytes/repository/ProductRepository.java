package com.example.EcoBytes.repository;


import com.example.EcoBytes.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}

