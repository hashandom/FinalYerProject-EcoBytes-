package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.ReturnRecallRequestDto;
import com.example.EcoBytes.dto.ReturnRecallResponseDto;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.entity.ReturnRecall;
import com.example.EcoBytes.exception.ResourceNotFoundException;
import com.example.EcoBytes.mapper.ReturnRecallMapper;
import com.example.EcoBytes.repository.BatchRepository;
import com.example.EcoBytes.repository.ReturnRecallRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReturnRecallService {
    private final ReturnRecallRepository returnRecallRepository;
    private final BatchRepository batchRepository;

    // CREATE
    public ReturnRecallResponseDto createRecall(ReturnRecallRequestDto dto) {

        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        ReturnRecall recall = ReturnRecallMapper.toEntity(dto, batch);
        // generate recall id
        recall.setRecallId(generateRecallId());
        ReturnRecall saved = returnRecallRepository.save(recall);
        return ReturnRecallMapper.toDTO(saved);
    }


    // GET ALL
    public List<ReturnRecallResponseDto> getAllRecalls() {
        return returnRecallRepository.findAll()
                .stream()
                .map(ReturnRecallMapper::toDTO)
                .collect(Collectors.toList());
    }


    // GET BY ID
    public ReturnRecallResponseDto getRecallById(String recallId) {
        ReturnRecall recall = returnRecallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with id " + recallId));
        return ReturnRecallMapper.toDTO(recall);
    }


    // GET BY BATCH
    public List<ReturnRecallResponseDto> getRecallsByBatch(String batchId) {
        List<ReturnRecall> recalls = returnRecallRepository.findByBatch_BatchCode(batchId);
        if (recalls.isEmpty()) {
            throw new ResourceNotFoundException("No recalls found for batch " + batchId);
        }
        return recalls.stream()
                .map(ReturnRecallMapper::toDTO)
                .collect(Collectors.toList());
    }


    // UPDATE
    public ReturnRecallResponseDto updateRecall(String recallId, ReturnRecallRequestDto dto) {
        ReturnRecall existing = returnRecallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with id " + recallId));
        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found"));
        existing.setReason(dto.getReason());
        existing.setDate(dto.getDate());
        existing.setBatch(batch);
        ReturnRecall saved = returnRecallRepository.save(existing);
        return ReturnRecallMapper.toDTO(saved);
    }


    // DELETE
    public void deleteRecall(String recallId) {
        ReturnRecall recall = returnRecallRepository.findById(recallId)
                .orElseThrow(() -> new ResourceNotFoundException("Recall not found with id " + recallId));
        returnRecallRepository.delete(recall);
    }

    // -------------------------------
    // PRIVATE METHOD
    // -------------------------------

    private String generateRecallId() {

        ReturnRecall lastRecall = returnRecallRepository.findTopByOrderByRecallIdDesc();
        if (lastRecall == null || lastRecall.getRecallId() == null) {
            return "RETURNRECALL-001";
        }
        String lastId = lastRecall.getRecallId();
        int number = Integer.parseInt(lastId.split("-")[1]);
        number++;
        return String.format("RETURNRECALL-%03d", number);
    }
}
