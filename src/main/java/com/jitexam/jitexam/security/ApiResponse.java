package com.jitexam.jitexam.security;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private int status;
    private String message;
    private Object result;

    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
