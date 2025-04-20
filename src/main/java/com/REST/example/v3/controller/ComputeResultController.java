package com.REST.example.v3.controller;

import com.REST.example.dto.OptionCount;
import com.REST.example.dto.VoteResult;
import com.REST.example.model.Vote;
import com.REST.example.v3.repository.VoteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("computeResultControllerV3")
@RequestMapping("/v3")
@Tag(name = "compute-result-controller", description = "Controller poll result calculation")
public class ComputeResultController {
    private final VoteRepository voteRepository;

    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")
    @Operation(description = "require pollId in request params. Calculate result for specific poll", summary = "Compute poll result")
    @ApiResponse(responseCode = "200", description = "List of polls")
    public ResponseEntity<?> computeResult(@RequestParam(name = "pollId") Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findAllByPollId(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for(Vote vote : allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(vote.getOption().getId());
            if(optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                tempMap.put(vote.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());

        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }
}
