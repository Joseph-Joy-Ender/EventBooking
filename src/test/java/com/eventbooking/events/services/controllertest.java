package com.eventbooking.events.services;

import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class controllertest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateAccount() throws Exception {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Joseph Joy Ender");
        accountRequest.setPassword("Joseph1234JJ3245");
        accountRequest.setEmail("Joseph234@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(accountRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }
}
