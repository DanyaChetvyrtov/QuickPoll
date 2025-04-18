package com.REST.example.dto.error;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ErrorDetails {

    private String title;
    private int status;
    private String details;
    private long timeStamp;
    private String developerMessage;
    private Map<String, List<ValidationError>> errors = new HashMap<>();
}
