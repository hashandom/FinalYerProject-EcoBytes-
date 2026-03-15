package com.example.EcoBytes.entity;

import com.example.EcoBytes.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
