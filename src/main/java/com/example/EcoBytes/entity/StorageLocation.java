package com.example.EcoBytes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StorageLocation {

    @Id
    private String locationId;
    private String name;
    private String type; // COLD, FREEZER, SHELF
}
