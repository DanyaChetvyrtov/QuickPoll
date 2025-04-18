package com.REST.example.controller;

import com.REST.example.dto.OptionCount;
import com.REST.example.dto.VoteResult;
import com.REST.example.model.Vote;
import com.REST.example.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {
    private final VoteRepository voteRepository;

    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @GetMapping("/computeresult")
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
