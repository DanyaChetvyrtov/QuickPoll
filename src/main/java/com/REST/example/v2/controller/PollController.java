package com.REST.example.v2.controller;

import com.REST.example.dto.PollRequest;
import com.REST.example.model.Poll;
import com.REST.example.service.PollService;
import com.REST.example.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController("pollControllerV2")
@RequestMapping("/v2")
@Tag(name = "poll-controller", description = "Controller for poll entity manipulation")
public class PollController {
    private PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/polls")
    @Operation(description = "returns all the available polls in db", summary = "Returns polls")
    @ApiResponse(responseCode = "200", description = "List of polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        return new ResponseEntity<>(pollService.getAllPolls(), HttpStatus.OK);
    }

    @GetMapping("/polls/{pollId}")
    @Operation(description = "returns poll by given pollId", summary = "Returns poll")
    @ApiResponse(responseCode = "200", description = "Poll with requested id")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    public ResponseEntity<Poll> getPollById(@PathVariable("pollId") Long pollId) {
        return new ResponseEntity<>(pollService.getById(pollId), HttpStatus.OK);
    }

    @PostMapping("/polls")
    @Operation(description = "require new poll JSON in request body & create new entity from it", summary = "Creates new poll")
    @ApiResponse(responseCode = "201", description = "Poll created")
    @ApiResponse(responseCode = "400", description = "Validation failed")
    public ResponseEntity<Poll> createPoll(@RequestBody @Validated(OnCreate.class) PollRequest pollRequest) {
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
    @Operation(description = "require new poll JSON in request body & update existed entity from it", summary = "Update new poll")
    @ApiResponse(responseCode = "200", description = "Poll updated")
    @ApiResponse(responseCode = "400", description = "Validation failed")
    @ApiResponse(responseCode = "404", description = "Resource not found")
    public ResponseEntity<Poll> updatePoll(@PathVariable("pollId") Long pollId, @RequestBody Poll poll) {
        return new ResponseEntity<>(pollService.update(poll), HttpStatus.OK);
    }

    @DeleteMapping("/polls/{pollId}")
    @Operation(description = "require pollId & delete entity if it exists", summary = "Creates new poll")
    @ApiResponse(responseCode = "200", description = "Poll deleted")
    public ResponseEntity<?> deletePoll(@PathVariable("pollId") Long pollId) {
        pollService.delete(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
