package com.example.EcoBytes.exception;

import com.example.EcoBytes.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.EcoBytes.response.ApiResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptioinHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .errorCode("RESOURCE_NOT_FOUND")
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    // RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<String> handleRuntime(RuntimeException ex){
        return ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> handleValidation(MethodArgumentNotValidException ex){

        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ApiResponse.<String>builder()
                .success(false)
                .message(errors)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Wrong username/password
    @ExceptionHandler(BadCredentialsException.class)
    public ApiResponse<String> handleBadCredentials(BadCredentialsException ex){
        return ApiResponse.<String>builder()
                .success(false)
                .message("Invalid username or password")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResponse<String> handleUserNotFound(UsernameNotFoundException ex){
        return ApiResponse.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    // Fallback for any other exception
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleOtherExceptions(Exception ex){
        return ApiResponse.<String>builder()
                .success(false)
                .message("Something went wrong: " + ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
