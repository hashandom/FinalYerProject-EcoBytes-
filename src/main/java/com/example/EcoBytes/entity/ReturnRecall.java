package com.example.EcoBytes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class ReturnRecall {
    @Id
    private String recallId;

    private String reason;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;
}
