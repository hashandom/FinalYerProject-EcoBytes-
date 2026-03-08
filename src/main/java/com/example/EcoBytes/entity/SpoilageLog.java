package com.example.EcoBytes.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "spoilage_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SpoilageLog {

    @Id
    private String spoilageId;
    private String reason;
    private Integer quantity;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;
}
