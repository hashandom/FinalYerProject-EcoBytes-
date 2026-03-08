package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.ProductRequestDto;
import com.example.EcoBytes.dto.ProductResponseDto;
import com.example.EcoBytes.entity.Product;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.mapper.ProductMapper;
import com.example.EcoBytes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponseDto save(ProductRequestDto dto) {

        Product product = ProductMapper.toEntity(dto);
        long count = productRepository.count() + 1;
        String id = String.format("PRD-%03d", count);
        product.setProductId(id);
        product = productRepository.save(product);
        return ProductMapper.toDTO(product);
    }


    public List<ProductResponseDto> getAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductResponseDto getById(String id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toDTO(product);
    }

    public ProductResponseDto update(String id, ProductRequestDto dto) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
        existing.setName(dto.getName());
        existing.setCategory(dto.getCategory());
        existing.setUnitPrice(dto.getUnitPrice());
        existing.setReorderLevel(dto.getReorderLevel());
        existing = productRepository.save(existing);
        return ProductMapper.toDTO(existing);
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

}
