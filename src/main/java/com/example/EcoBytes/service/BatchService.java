package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.BatchRequestDto;
import com.example.EcoBytes.dto.BatchResponseDto;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.entity.Product;
import com.example.EcoBytes.entity.Supplier;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.mapper.BatchMapper;
import com.example.EcoBytes.repository.BatchRepository;
import com.example.EcoBytes.repository.ProductRepository;
import com.example.EcoBytes.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    // CREATE
    public BatchResponseDto createBatch(BatchRequestDto dto) {

        Batch batch = BatchMapper.toEntity(dto);

        // generate batch code
        batch.setBatchCode(generateBatchCode());

        // fetch product
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        batch.setProduct(product);

        // fetch supplier if provided
        if (dto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
            batch.setSupplier(supplier);
        }

        Batch saved = batchRepository.save(batch);

        return BatchMapper.toDto(saved);
    }

    // GET ALL
    public List<BatchResponseDto> getAllBatches() {
        return batchRepository.findAll()
                .stream()
                .map(BatchMapper::toDto)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public BatchResponseDto getBatchById(String batchCode) {
        Batch batch = batchRepository.findById(batchCode)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with code " + batchCode));
        return BatchMapper.toDto(batch);
    }

    // UPDATE
    public BatchResponseDto updateBatch(String batchCode, BatchRequestDto dto) {

        Batch existing = batchRepository.findById(batchCode)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with code " + batchCode));

        // update fields
        BatchMapper.updateEntity(existing, dto);

        // fetch product
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existing.setProduct(product);

        // fetch supplier if provided
        if (dto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
            existing.setSupplier(supplier);
        }

        Batch saved = batchRepository.save(existing);

        return BatchMapper.toDto(saved);
    }

    // DELETE
    public void deleteBatch(String batchCode) {

        Batch batch = batchRepository.findById(batchCode)
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with code " + batchCode));

        batchRepository.delete(batch);
    }

    // FEFO - First Expired First Out
    public List<BatchResponseDto> getFEFOBatches(String productId) {

        List<Batch> batches = batchRepository.findByProductProductIdOrderByExpiryDateAsc(productId);

        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for product ID " + productId);
        }

        return batches.stream()
                .map(BatchMapper::toDto)
                .collect(Collectors.toList());
    }

    // LOW STOCK ALERT SYSTEM
    public List<BatchResponseDto> getLowStockBatches() {

        List<Batch> lowStock = batchRepository.findAll()
                .stream()
                .filter(b -> b.getQuantity() < b.getProduct().getReorderLevel())
                .collect(Collectors.toList());

        return lowStock.stream()
                .map(BatchMapper::toDto)
                .collect(Collectors.toList());
    }

    // -------------------------------
    // PRIVATE METHODS
    // -------------------------------

    private String generateBatchCode() {

        Batch lastBatch = batchRepository.findTopByOrderByBatchCodeDesc();

        if (lastBatch == null || lastBatch.getBatchCode() == null) {
            return "BATCH-001";
        }

        String lastCode = lastBatch.getBatchCode();
        int number = Integer.parseInt(lastCode.split("-")[1]);
        number++;

        return String.format("BATCH-%03d", number);
    }
}