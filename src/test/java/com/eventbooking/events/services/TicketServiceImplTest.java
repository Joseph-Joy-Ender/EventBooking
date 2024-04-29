package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.TicketCategory;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.ReserveTicketResponse;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Slf4j
class TicketServiceImplTest {

    @Autowired
    private TicketService ticketService;
    @Test
    public void testThatTicketCanBeCreated() throws EventExistException {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCategory(TicketCategory.EARLYBIRD);
        request.setPrice(BigDecimal.valueOf(20000));

        TicketResponse response = ticketService.createTicket(1L, request);
        assertThat(response).isNotNull();

    }
    @Test
    public void testThatTicketCanBeCreated2() throws EventExistException {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCategory(TicketCategory.VIP);
        request.setPrice(BigDecimal.valueOf(50000));

        TicketResponse response = ticketService.createTicket(2L, request);
        assertThat(response).isNotNull();

    }
    @Test
    public void testThatTicketCanBeCreated3() throws EventExistException {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCategory(TicketCategory.PREMIUM);
        request.setPrice(BigDecimal.valueOf(40000));

        TicketResponse response = ticketService.createTicket(3L, request);
        assertThat(response).isNotNull();

    }

    @Test
    public void testThatTicketCanBeSearched() throws TicketException {
        String eventName = "Burna Boy Concert";
        List<Ticket> ticket = ticketService.searchTicketBy(eventName);
        log.info("All Tickets found --> {}", ticket);
        assertThat(ticket).hasSize(1);
        assertThat(ticket).isNotNull();
    }

    @Test
    public void testThatExceptionIsThrownWhenTicketIsNotFound(){
        String eventName = "Birthday party bash";
        assertThrows(TicketException.class, ()->ticketService.searchTicketBy(eventName));

    }

    @Test
    public void testThatATicketCanBeReserved() throws UserException {

        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setUserId(3L);
        request.setEventId(1L);

         ReserveTicketResponse response = ticketService.reserveTicket(request);
         assertThat(response).isNotNull();
         assertThat(request.getUserId()).isNotNull();
    }

    @Test
    public void testThatTicketCanBeReservedAgain() throws UserException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setUserId(4L);
        request.setEventId(2L);

        ReserveTicketResponse response = ticketService.reserveTicket(request);
        log.info("Reserved Ticket Response{}", response);
        assertThat(response).isNotNull();
        assertThat(request.getUserId()).isNotNull();
    }

    @Test
    public void testThatTicketCanBeBooked(){
        //TODO
        //first check if the ticket is reserved
    }

}