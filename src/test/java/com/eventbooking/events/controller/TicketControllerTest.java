package com.eventbooking.events.controller;

import com.eventbooking.events.data.model.TicketCategory;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testThatTicketCanBeCreated() throws Exception {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setPrice(BigDecimal.valueOf(50000));
        request.setCategory(TicketCategory.PREMIUM);
        request.setEventId(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createTicket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void testThatTicketCanBeCreatedAgain() throws Exception {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setPrice(BigDecimal.valueOf(100000));
        request.setCategory(TicketCategory.VIP);
        Long eventId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createTicket/{eventId}", eventId)
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writeValueAsBytes(request)))
               .andExpect(status().is2xxSuccessful())
               .andDo(print());
    }

    @Test
    public void testThatTicketCanBeSearchedForUsingTheEventName() throws Exception {
        String eventName = "Birthday party";

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/eventBooking/searchTicketBy/{eventName}", eventName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
