package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.SpoilageRequestDto;
import com.example.EcoBytes.dto.SpoilageResponseDto;
import com.example.EcoBytes.service.SpoilageLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spoilage")
@RequiredArgsConstructor
public class SpoilageLogController {

    private final SpoilageLogService spoilageService;

    @PostMapping
    public SpoilageResponseDto create(@Valid @RequestBody SpoilageRequestDto dto){
        return spoilageService.createSpoilage(dto);
    }
}
