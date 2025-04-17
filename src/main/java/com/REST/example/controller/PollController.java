package com.REST.example.controller;

import com.REST.example.dto.OptionRequest;
import com.REST.example.dto.PollRequest;
import com.REST.example.model.Poll;
import com.REST.example.service.PollService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PollController {
    private PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        return new ResponseEntity<>(pollService.getAllPolls(), HttpStatus.OK);
    }

    @GetMapping("/polls/{pollId}")
    public ResponseEntity<Poll> getPollById(@PathVariable("pollId") Long pollId) {
        return new ResponseEntity<>(pollService.getById(pollId), HttpStatus.OK);
    }

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody PollRequest pollRequest) {
        Poll res = pollService.addPoll(pollRequest);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(res.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);

        return new ResponseEntity<>(res, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/polls/{pollId}")
    public ResponseEntity<Poll> updatePoll(@PathVariable("pollId") Long pollId, @RequestBody Poll poll) {
        return new ResponseEntity<>(pollService.update(poll), HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable("pollId") Long pollId){
        pollService.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
