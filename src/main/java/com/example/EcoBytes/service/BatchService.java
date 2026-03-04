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

    public List<Batch> getFEFOBatches(Long productId) {

        List<Batch> batches =
                batchRepository.findByProductProductIdOrderByExpiryDateAsc(productId);

        if (batches.isEmpty()) {
            throw new ResourceNotFoundException("No batches found for product ID: " + productId);
        }

        return batches;
    }
}
