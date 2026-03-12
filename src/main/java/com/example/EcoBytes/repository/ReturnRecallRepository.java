package com.example.EcoBytes.repository;

import com.example.EcoBytes.entity.ReturnRecall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

    public interface ReturnRecallRepository extends JpaRepository<ReturnRecall, String> {
        // Corrected: use Batch.batchCode (not batchId)
        List<ReturnRecall> findByBatch_BatchCode(String batchCode);

        // Get the last created ReturnRecall for generating IDs
        ReturnRecall findTopByOrderByRecallIdDesc();
    }

