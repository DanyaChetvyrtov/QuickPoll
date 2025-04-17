package com.REST.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class PollRequest {
    private String question;
    private List<OptionRequest> options;
}
