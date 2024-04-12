package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.TicketCategory;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TicketServiceImplTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;
    @Test
    public void testThatTicketCanBeCreated() throws EventExistException {
//        CreateTicketRequest request = new CreateTicketRequest();
//        request.setCategory(TicketCategory.EARLYBIRD);
//        request.setPrice(BigDecimal.valueOf(20000));
//        Event event = eventService.findEventBy(request.getEventId());
//        request.setDate(event.getDate());
//        request.setEventName(event.getEventName());

//        TicketResponse response = ticketService.createTicket(request);
//        assertThat(response).isNotNull();



    }

}