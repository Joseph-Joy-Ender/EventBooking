package com.eventbooking.events.controller;

import com.eventbooking.events.data.model.Category;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class CustomerControllerTest {

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

    @Test
    public void testCreateAccountValidation() throws Exception {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Udeme Akpan");
        accountRequest.setPassword("UdemeAkpan123");
        accountRequest.setEmail("UdemeAkpan11234@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(accountRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }


    @Test
    public void testCreateAccountValidationCharacter() throws Exception {
        CreateAccountRequest accountRequest = new CreateAccountRequest();
        accountRequest.setName("Precious Ujong");
        accountRequest.setPassword("Precious123Ujong");
        accountRequest.setEmail("PreciousUjong11234@gmail.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/createAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(accountRequest)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());

    }

    @Test
    public void testThatUserCanCreateEvent() throws Exception {
        AddEventRequest request = new AddEventRequest();
        request.setEmail("FavourMbata@gmail.com");
        request.setEventName("Scrabble Tournament");
        request.setAttendees(950);
        request.setDate(LocalDate.of(2025, 1, 12));
        request.setCategory("GAME");
        request.setDescription("Scrabble game tournament to enhance learning");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/eventBooking/addEvent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(request)))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void testThatUserCanSearchTicketByEventName(){

    }
}
