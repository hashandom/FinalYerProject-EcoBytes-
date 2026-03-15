package com.example.EcoBytes.controller;

import com.example.EcoBytes.dto.AuthRequestDto;
import com.example.EcoBytes.dto.AuthResponseDto;
import com.example.EcoBytes.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {

        return authService.login(request);
    }
}