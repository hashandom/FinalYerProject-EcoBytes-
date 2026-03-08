package com.example.EcoBytes.repository;

import com.example.EcoBytes.entity.SpoilageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpoilageLogRepository extends JpaRepository<SpoilageLog, String> {

    SpoilageLog findTopByOrderBySpoilageIdDesc();
}
