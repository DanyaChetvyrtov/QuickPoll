package com.REST.example.unit;

import com.REST.example.ExampleApplication;
import com.REST.example.model.Option;
import com.REST.example.model.Poll;
import com.REST.example.v1.controller.PollController;
import com.REST.example.v1.service.PollService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ExampleApplication.class)
@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PollControllerTest {
    PollController pollController;

    @Mock
    private PollService pollService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        pollController = new PollController(pollService);
        mockMvc = standaloneSetup(pollController).build();
    }

    @Test
    public void testGetAllPolls() throws Exception {
        when(pollService.getAllPolls()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/v1/polls"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testGetPoll() throws Exception {
        var testPoll = new Poll();
        testPoll.setId(1L);
        testPoll.setQuestion("Test question");

        var options = new LinkedHashSet<Option>();
        options.add(new Option(1L, "option 1"));
        options.add(new Option(2L, "option 2"));
        options.add(new Option(3L, "option 3"));
        options.add(new Option(4L, "option 4"));
        testPoll.setOptions(options);

        var test = "{\"id\":1," +
                "\"question\":\"Test question\"," +
                "\"options\":[" +
                "{\"id\":1,\"value\":\"option 1\"}," +
                "{\"id\":2,\"value\":\"option 2\"}," +
                "{\"id\":3,\"value\":\"option 3\"}," +
                "{\"id\":4,\"value\":\"option 4\"}]}";
        when(pollService.getById(1L)).thenReturn(testPoll);
        mockMvc.perform(get("/v1/polls/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(test));
    }
}
