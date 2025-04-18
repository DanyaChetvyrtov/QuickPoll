package com.REST.example.dto.error;

import lombok.Data;

@Data
public class ValidationError {
    private String constraint;
    private String message;
}
