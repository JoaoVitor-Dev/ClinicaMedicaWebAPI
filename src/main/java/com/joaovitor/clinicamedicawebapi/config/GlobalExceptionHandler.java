package com.joaovitor.clinicamedicawebapi.config;


import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import com.joaovitor.clinicamedicawebapi.exception.RegraNegocioException;
import com.joaovitor.clinicamedicawebapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiResponse<String>> handleRegraNegocio(RegraNegocioException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler({ NoSuchElementException.class, ResourceNotFoundException.class })
    public ResponseEntity<ApiResponse<String>> handleNotFound(RuntimeException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleExceptionGenerica(Exception ex) {
        ex.printStackTrace();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(
                        false,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Ocorreu um erro interno no servidor. Contate o suporte."
                ));
    }
}