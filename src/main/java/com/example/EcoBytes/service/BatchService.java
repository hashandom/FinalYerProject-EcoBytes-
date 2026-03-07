package com.example.EcoBytes.service;

import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.entity.Product;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.repository.BatchRepository;
import com.example.EcoBytes.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;

    // CREATE
    public Batch createBatch(Batch batch) {
        long count = batchRepository.count() + 1;
        String batchCode = String.format("BATCH-%03d", count);
        batch.setBatchCode(batchCode);
        return batchRepository.save(batch);
    }

    // GET ALL
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    // GET BY ID
    public Batch getBatchById(String id) {
        return batchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Batch not found with id " + id));
    }

    // UPDATE
    public Batch updateBatch(String id, Batch batch) {

        Batch existing = getBatchById(id);

        existing.setManufactureDate(batch.getManufactureDate());
        existing.setExpiryDate(batch.getExpiryDate());
        existing.setQuantity(batch.getQuantity());

        // fetch full product from DB
        Product product = productRepository.findById(batch.getProduct().getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setProduct(product);

        return batchRepository.save(existing);
    }

    // DELETE
    public void deleteBatch(String id) {

        Batch batch = getBatchById(id);

        batchRepository.delete(batch);
    }

    // FEFO
    public List<Batch> getFEFOBatches(String productId) {

        List<Batch> batches =
                batchRepository.findByProductProductIdOrderByExpiryDateAsc(productId);

        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for product ID " + productId);
        }

        return batches;
    }

    //LOW STOCK ALERT SYSTEM
    public List<Batch> getLowStock() {
        return batchRepository.findAll()
                .stream()
                .filter(b -> b.getQuantity() < b.getProduct().getReorderLevel())
                .toList();
    }
}