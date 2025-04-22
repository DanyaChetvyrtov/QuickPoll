package com.REST.example.integration;


import com.REST.example.ExampleApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = ExampleApplication.class)
@WebAppConfiguration
public class PollControllerInteg {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void testGetAllPolls() throws Exception {
        mockMvc.perform(get("/v1/polls"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }
}
