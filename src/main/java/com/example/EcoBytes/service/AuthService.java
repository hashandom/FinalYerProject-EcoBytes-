package com.example.EcoBytes.service;


import com.example.EcoBytes.dto.AuthRequestDto;
import com.example.EcoBytes.dto.AuthResponseDto;
import com.example.EcoBytes.security.JwtService;
import com.example.EcoBytes.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // injected bean

    public AuthResponseDto login(AuthRequestDto request){

        // authenticate username & password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // load user from database
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(request.getUsername());

        // generate JWT token
        String token = jwtService.generateToken(userDetails);

        return new AuthResponseDto(token);
    }
}
