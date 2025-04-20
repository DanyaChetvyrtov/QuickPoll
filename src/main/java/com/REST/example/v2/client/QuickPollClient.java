package com.REST.example.v2.client;

import com.REST.example.model.Poll;
import com.REST.example.v1.client.wrapper.PageWrapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class QuickPollClient {
    private static final String QUICK_POLL_URI_V2 = "http://localhost:8080/v2/polls";
    private static final RestTemplate restTemplate = new RestTemplate();


    public static PageWrapper<Poll> getPolls(int page, int size) {
        var responseType = new ParameterizedTypeReference<PageWrapper<Poll>>() {};

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(QUICK_POLL_URI_V2)
                .queryParam("page", page).queryParam("size", size);

        var responseEntity = restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
        return responseEntity.getBody();
    }

    public static void main(String[] args) {
        var polls = getPolls(0, 3);
        polls.getContent().forEach(System.out::println);

        System.out.println("Divider");

        polls = getPolls(1, 3);
        polls.getContent().forEach(System.out::println);

    }
}
