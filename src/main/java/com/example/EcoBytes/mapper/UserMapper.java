package com.example.EcoBytes.mapper;

import com.example.EcoBytes.dto.UserRequestDto;
import com.example.EcoBytes.dto.UserResponseDto;
import com.example.EcoBytes.entity.User;

public class UserMapper {
    public static User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public static UserResponseDto toDto(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }
}
