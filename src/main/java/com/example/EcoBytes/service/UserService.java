package com.example.EcoBytes.service;

import com.example.EcoBytes.dto.UserRequestDto;
import com.example.EcoBytes.dto.UserResponseDto;
import com.example.EcoBytes.entity.User;
import com.example.EcoBytes.mapper.UserMapper;
import com.example.EcoBytes.repository.UserRepository;
import com.example.EcoBytes.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiResponse<UserResponseDto> createUser(UserRequestDto dto) {
        User user = UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(user);
        return new ApiResponse<>(
                true,
                "User created successfully",
                UserMapper.toDto(saved),
                LocalDateTime.now()
        );
    }


    public ApiResponse<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
        return new ApiResponse<>(
                true,
                "User list",
                users,
                LocalDateTime.now()
        );
    }


    public ApiResponse<UserResponseDto> getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new ApiResponse<>(
                true,
                "User found",
                UserMapper.toDto(user),
                LocalDateTime.now()
        );
    }


    public ApiResponse<UserResponseDto> updateUser(Long id, UserRequestDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        User saved = userRepository.save(user);
        return new ApiResponse<>(
                true,
                "User updated",
                UserMapper.toDto(saved),
                LocalDateTime.now()
        );
    }


    public ApiResponse<String> deleteUser(Long id) {
        userRepository.deleteById(id);
        return new ApiResponse<>(
                true,
                "User deleted",
                null,
                LocalDateTime.now()
        );
    }
}
