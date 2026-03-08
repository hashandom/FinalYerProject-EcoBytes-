package com.example.EcoBytes.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;


@Entity
@Data
public class Supplier {
    @Id
    private String supplierId;

    private String name;

    private String contact;

    private Double rating;

    private Integer avgDeliveryTime;

    private Double qualityScore;

    private Double cost;

    // ONE SUPPLIER → MANY BATCHES
    @OneToMany(mappedBy = "supplier")
    @JsonManagedReference
    private List<Batch> batches;
}
