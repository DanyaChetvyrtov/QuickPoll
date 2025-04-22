package com.REST.example.unit;

import com.REST.example.model.Poll;
import com.REST.example.v1.controller.PollController;
import com.REST.example.v1.service.PollService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PollControllerTestMock {
    @Mock
    private PollService pollService;

    @Test
    public void testGetAllPolls() {
        PollController pollController = new PollController(pollService);

        when(pollService.getAllPolls()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Poll>> allPollsEntity = pollController.getAllPolls();
        verify(pollService, times(1)).getAllPolls();

        assertEquals(HttpStatus.OK, allPollsEntity.getStatusCode());
        assertEquals(0, Objects.requireNonNull(Lists.newArrayList(allPollsEntity.getBody())).size());
    }
}
