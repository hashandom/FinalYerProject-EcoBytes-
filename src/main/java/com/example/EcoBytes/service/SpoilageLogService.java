package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.SpoilageRequestDto;
import com.example.EcoBytes.dto.SpoilageResponseDto;
import com.example.EcoBytes.entity.Batch;
import com.example.EcoBytes.entity.SpoilageLog;
import com.example.EcoBytes.mapper.SpoilageMapper;
import com.example.EcoBytes.repository.BatchRepository;
import com.example.EcoBytes.repository.SpoilageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class SpoilageLogService {

    private final SpoilageLogRepository spoilageRepository;
    private final BatchRepository batchRepository;

    public SpoilageResponseDto createSpoilage(SpoilageRequestDto dto) {

        Batch batch = batchRepository.findById(dto.getBatchId())
                .orElseThrow(() -> new RuntimeException("Batch not found"));

        SpoilageLog log = new SpoilageLog();
        log.setSpoilageId(generateSpoilageId());
        log.setReason(dto.getReason());
        log.setQuantity(dto.getQuantity());
        log.setDate(LocalDate.now());
        log.setBatch(batch);

        spoilageRepository.save(log);

        return SpoilageMapper.toDTO(log);
    }


    private String generateSpoilageId() {

        SpoilageLog last = spoilageRepository.findTopByOrderBySpoilageIdDesc();

        if (last == null) {
            return "SPOL-001";
        }

        int num = Integer.parseInt(last.getSpoilageId().split("-")[1]);
        return String.format("SPOL-%03d", num + 1);
    }
}
