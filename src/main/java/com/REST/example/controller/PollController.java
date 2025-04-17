package com.REST.example.controller;

import com.REST.example.repository.PollRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PollController {
    private PollRepository pollRepository;

    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }
}
