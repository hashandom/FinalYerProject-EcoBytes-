package com.example.EcoBytes.mapper;


import com.example.EcoBytes.dto.ProductRequestDto;
import com.example.EcoBytes.dto.ProductResponseDto;
import com.example.EcoBytes.entity.Product;

public class ProductMapper {
    public static Product toEntity(ProductRequestDto dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setUnitPrice(dto.getUnitPrice());
        product.setReorderLevel(dto.getReorderLevel());

        return product;
    }

    public static ProductResponseDto toDTO(Product product) {

        ProductResponseDto dto = new ProductResponseDto();

        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setUnitPrice(product.getUnitPrice());
        dto.setReorderLevel(product.getReorderLevel());

        return dto;
    }
}
