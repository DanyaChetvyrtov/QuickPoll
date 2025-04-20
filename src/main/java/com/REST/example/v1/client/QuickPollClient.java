package com.REST.example.v1.client;

import com.REST.example.model.Option;
import com.REST.example.model.Poll;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class QuickPollClient {
    private static final String QUICK_POLL_URI_V1 = "http://localhost:8080/v1/polls";
    private static final RestTemplate restTemplate = new RestTemplate();

    public static Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_V1 + "/" + pollId, Poll.class);
    }

    public static List<Poll> getPolls() {
        var responseType = new ParameterizedTypeReference<List<Poll>>() {};
        var responseEntity = restTemplate.exchange(QUICK_POLL_URI_V1, HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }

    public static Poll createPoll(Poll poll) {
        return restTemplate.postForObject(QUICK_POLL_URI_V1, poll, Poll.class);
    }

    public static void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_V1 + "/" + poll.getId(), poll);
    }

    public static void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_V1 + "/" + pollId);
    }

    public static void main(String[] args) {
        Poll poll = getPollById(1L);
        System.out.println(poll);

        List<Poll> polls = getPolls();
        polls.forEach(System.out::println);

        Poll newPoll = new Poll();
        newPoll.setQuestion("It's a test question");
        newPoll.getOptions().add(new Option("A"));
        newPoll.getOptions().add(new Option("B"));
        newPoll.getOptions().add(new Option("C"));

        newPoll = createPoll(newPoll);
        System.out.println(newPoll);

        newPoll.setQuestion(newPoll.getQuestion() + "!!! UPDATED !!!");
        updatePoll(newPoll);
        System.out.println(getPollById(newPoll.getId()));
    }
}
