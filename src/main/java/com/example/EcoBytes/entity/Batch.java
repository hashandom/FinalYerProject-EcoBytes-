package com.example.EcoBytes.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Batch {
    @Id
    @Column(nullable = false, unique = true)
    private String batchCode;   // PRIMARY KEY

    @NotNull(message = "Manufacture date is required")
    @Column(nullable = false)
    private LocalDate manufactureDate;

    @NotNull(message = "Expiry date is required")
    @Column(nullable = false)
    private LocalDate expiryDate;

    @NotNull(message = "Quantity is required")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "Product is required")
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // MANY BATCHES → ONE SUPPLIER
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;

}
