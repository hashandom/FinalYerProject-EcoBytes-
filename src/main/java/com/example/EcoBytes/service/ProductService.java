package com.example.EcoBytes.service;

import com.example.EcoBytes.entity.Product;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        long count = productRepository.count() + 1;
        String id = String.format("PRD-%03d", count);

        product.setProductId(id);
        return productRepository.save(product);
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product update(String id, Product product) {

        Product existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with id: " + id));

        existing.setName(product.getName());
        existing.setUnitPrice(product.getUnitPrice());

        return productRepository.save(existing);
    }

    public void delete(String id) {
         productRepository.deleteById(id);
    }

}
