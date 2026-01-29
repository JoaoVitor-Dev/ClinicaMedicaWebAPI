package com.joaovitor.clinicamedicawebapi.api;

public class ApiResponse<T> {
    private boolean success;
    private int statusCode;
    private T data;

    public ApiResponse(boolean success, int statusCode, T data) {
        this.success = success;
        this.statusCode = statusCode;
        this.data = data;
    }

    public ApiResponse() {
    }


}