package com.REST.example.v3.service;

import com.REST.example.dto.PollRequest;
import com.REST.example.model.Option;
import com.REST.example.model.Poll;
import com.REST.example.model.exception.ResourceNotFoundException;
import com.REST.example.v3.repository.OptionRepository;
import com.REST.example.v3.repository.PollRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("pollServiceV3")
@Transactional(readOnly = true)
public class PollService {
    private PollRepository pollRepository;
    private OptionRepository optionRepository;

    public PollService(PollRepository pollRepository, OptionRepository optionRepository) {
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
    }

    public Page<Poll> getAllPolls(Pageable pageable) {
        return pollRepository.findAll(pageable);
    }

    public Poll getById(Long pollId) {
        return pollRepository.findById(pollId).orElseThrow(
                () -> new ResourceNotFoundException("Poll not found with id: " + pollId)
        );
    }

    @Transactional
    public Poll addPoll(PollRequest pollRequest) {
        Poll poll = new Poll();

        poll.setQuestion(pollRequest.getQuestion());

        Poll savedPoll = pollRepository.save(poll);
        pollRequest.getOptions().forEach(option -> {
            Option savedOption = new Option();
            savedOption.setValue(option.getValue());
            savedOption = optionRepository.save(savedOption);
            savedPoll.getOptions().add(savedOption);
        });

        return pollRepository.save(poll);
    }

    @Transactional
    public Poll update(Poll poll) {
        pollIsExist(poll.getId());
        return pollRepository.save(poll);
    }

    @Transactional
    public void delete(Long pollId) {
        pollRepository.deleteById(pollId);
    }

    private void pollIsExist(Long pollId) {
        if (!pollRepository.existsById(pollId))
            throw new ResourceNotFoundException("Poll not found with id: " + pollId);
    }
}
