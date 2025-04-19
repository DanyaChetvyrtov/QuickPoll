package com.REST.example.v2.controller;

import com.REST.example.model.Vote;
import com.REST.example.v2.repository.VoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController("voteControllerV2")
@RequestMapping("/v2")
@Tag(name = "vote-controller", description = "Controller for Vote entity")
public class VoteController {
    private final VoteRepository voteRepository;

    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @PostMapping("polls/{pollId}/votes")
    @Operation(description = "Register new vote. Required Vote in request body", summary = "Vote for poll")
    @ApiResponse(responseCode = "201", description = "Create new vote")
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
    @Operation(description = "Required pollId and return all votes tied to poll with such pollId", summary = "Poll votes")
    @ApiResponse(responseCode = "200", description = "Return vote list")
    public ResponseEntity<List<Vote>> getVotes(@PathVariable Long pollId) {
        List<Vote> res = new ArrayList<>();
        voteRepository.findAllByPollId(pollId).forEach(res::add);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
