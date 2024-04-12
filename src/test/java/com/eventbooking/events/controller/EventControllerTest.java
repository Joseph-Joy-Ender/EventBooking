package com.eventbooking.events.controller;

import com.eventbooking.events.data.model.Category;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testThatEventCanBeCreated() throws Exception {
        AddEventRequest request = new AddEventRequest();
        request.setEventName("Birthday Bash");
        request.setAttendees(200);
        request.setDate(LocalDate.of(2024, 5, 12));
        request.setDescription("Birthday bash party for a friend");
        request.setCategory(String.valueOf(Category.BIRTHDAY));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }
}
