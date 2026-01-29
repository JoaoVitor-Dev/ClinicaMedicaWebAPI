package com.joaovitor.clinicamedicawebapi.controller;

import com.joaovitor.clinicamedicawebapi.api.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {

    protected <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), data));
    }

    protected <T> ResponseEntity<ApiResponse<Page<T>>> okPage(Page<T> page) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ApiResponse<>(true, HttpStatus.OK.value(), page));
    }

    protected <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, HttpStatus.CREATED.value(), data));
    }

    protected <T> ResponseEntity<ApiResponse<T>> notFound(String msg) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, HttpStatus.NOT_FOUND.value(), (T) msg));
    }
}
