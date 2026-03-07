package com.example.EcoBytes.service;

import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {

    private final BatchRepository batchRepository;

    // CREATE
    public Batch createBatch(Batch batch) {
        return batchRepository.save(batch);
    }

    // GET ALL
    public List<Batch> getAllBatches() {
        return batchRepository.findAll();
    }

    // GET BY ID
    public Batch getBatchById(Long id) {
        return batchRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Batch not found with id " + id));
    }

    // UPDATE
    public Batch updateBatch(Long id, Batch batch) {

        Batch existing = getBatchById(id);

        existing.setManufactureDate(batch.getManufactureDate());
        existing.setExpiryDate(batch.getExpiryDate());
        existing.setQuantity(batch.getQuantity());
        existing.setProduct(batch.getProduct());

        return batchRepository.save(existing);
    }

    // DELETE
    public void deleteBatch(Long id) {

        Batch batch = getBatchById(id);

        batchRepository.delete(batch);
    }

    // FEFO
    public List<Batch> getFEFOBatches(Long productId) {

        List<Batch> batches =
                batchRepository.findByProductProductIdOrderByExpiryDateAsc(productId);

        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for product ID " + productId);
        }

        return batches;
    }
}