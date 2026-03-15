package com.example.EcoBytes.dto;

import com.example.EcoBytes.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long userId;
    private String username;
    private Role role;
}
