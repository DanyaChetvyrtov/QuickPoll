package com.REST.example.controller;

import com.REST.example.model.Vote;
import com.REST.example.repository.VoteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VoteController {
    private final VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @PostMapping("polls/{pollId}/votes")
    public ResponseEntity<Vote> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.setLocation(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(vote.getId())
                        .toUri()
        );

        return new ResponseEntity<>(vote, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/polls/{pollId}/votes")
    public ResponseEntity<List<Vote>> getVotes(@PathVariable Long pollId) {
        List<Vote> res = new ArrayList<>();
        voteRepository.findAllByPollId(pollId).forEach(res::add);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
