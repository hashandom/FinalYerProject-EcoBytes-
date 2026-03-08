package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.SupplierRequestDto;
import com.example.EcoBytes.dto.SupplierResponseDto;
import com.example.EcoBytes.entity.Supplier;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.mapper.SupplierMapper;
import com.example.EcoBytes.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    //Generate Supplier IDS
    private String generateSupplierId() {
        // Get last supplier
        Supplier lastSupplier = supplierRepository.findTopByOrderBySupplierIdDesc();

        if (lastSupplier == null) {
            return "SUP-0001";
        } else {
            String lastId = lastSupplier.getSupplierId(); // e.g., "SUP-0012"
            int number = Integer.parseInt(lastId.split("-")[1]); // 12
            number++; // 13
            return String.format("SUP-%04d", number); // "SUP-0013"
        }
    }

    //create supplier
    public SupplierResponseDto createSupplier(SupplierRequestDto dto) {

        Supplier supplier = SupplierMapper.toEntity(dto);
        supplier.setSupplierId(generateSupplierId());
        supplier = supplierRepository.save(supplier);
        return SupplierMapper.toDTO(supplier);
    }

    //get all suppliers
    public List<SupplierResponseDto> getAllSuppliers() {

        return supplierRepository.findAll()
                .stream()
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    //get supplier By ID
    public SupplierResponseDto getSupplierById(String id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        return SupplierMapper.toDTO(supplier);
    }

    //update supplier
    public SupplierResponseDto updateSupplier(String id, SupplierRequestDto dto) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        supplier.setName(dto.getName());
        supplier.setContact(dto.getContact());
        supplier.setRating(dto.getRating());
        supplier.setAvgDeliveryTime(dto.getAvgDeliveryTime());
        supplier.setQualityScore(dto.getQualityScore());
        supplier.setCost(dto.getCost());

        supplierRepository.save(supplier);

        return SupplierMapper.toDTO(supplier);
    }


    //delete suppler
    public void deleteSupplier(String id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        supplierRepository.delete(supplier);
    }
}
