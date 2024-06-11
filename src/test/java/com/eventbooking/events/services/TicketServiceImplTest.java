package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.TicketCategory;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.BookTicketResponse;
import com.eventbooking.events.dtos.response.CancelReservedTicketResponse;
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
        request.setEventId(1L);

        TicketResponse response = ticketService.createTicket(request);
        assertThat(response).isNotNull();

    }
    @Test
    public void testThatTicketCanBeCreated2() throws EventExistException {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCategory(TicketCategory.VIP);
        request.setPrice(BigDecimal.valueOf(50000));
        request.setEventId(2L);

        TicketResponse response = ticketService.createTicket(request);
        assertThat(response).isNotNull();

    }
    @Test
    public void testThatTicketCanBeCreated3() throws EventExistException {
        CreateTicketRequest request = new CreateTicketRequest();
        request.setCategory(TicketCategory.PREMIUM);
        request.setPrice(BigDecimal.valueOf(40000));
        request.setEventId(3L);

        TicketResponse response = ticketService.createTicket(request);
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
    public void testThatUserCanSearchForTicket() throws TicketException, UserException {
        String eventName = "Birthday party";
        String email = "Cephas123@gmail.com";
        List<Ticket> ticket = ticketService.searchTicketBy(email, eventName);
        assertThat(ticket).hasSize(1);
        assertThat(ticket).isNotNull();

    }

    @Test
    public void testThatExceptionIsThrownWhenUserIsNotFound() {
        String eventName = "Birthday party";
        String email = "Raph123@gmail.com";
        assertThrows(UserException.class, ()-> ticketService.searchTicketBy(email, eventName));
    }

    @Test
    public void testThatATicketCanBeReserved() throws UserException {

        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setUserId(2L);
        request.setTicketId(4L);
        request.setReservationId(10L);

         ReserveTicketResponse response = ticketService.reserveTicket(request);
         assertThat(response).isNotNull();
         assertThat(request.getUserId()).isNotNull();
    }

    @Test
    public void testThatTicketCanBeReservedAgain() throws UserException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setUserId(1L);
        request.setTicketId(3L);
        request.setReservationId(12L);

        ReserveTicketResponse response = ticketService.reserveTicket(request);
        log.info("Reserved Ticket Response{}", response);
        assertThat(response).isNotNull();
        assertThat(request.getUserId()).isNotNull();
    }
    @Test
    public void testThatTicketCanBeReservedAgain2() throws UserException {
        ReserveTicketRequest request = new ReserveTicketRequest();
        request.setUserId(3L);
        request.setTicketId(5L);
        request.setReservationId(9L);

        ReserveTicketResponse response = ticketService.reserveTicket(request);
        assertThat(response).isNotNull();
        assertThat(request.getUserId()).isNotNull();
    }

    @Test
    public void testThatReservedTicketCanBeCancelled() throws TicketException {
        Long reservationId = 10L;
        CancelReservedTicketResponse response = ticketService.cancelReservedTicket(reservationId);
        assertThat(response).isNotNull();
    }

//    @Test
//    public void testThatTicketCanBeBooked(){
//        Long reservationId = 12L;
//        BookTicketResponse response = ticketService.bookTicket(reservationId);
//        assertThat(response).isNotNull();
//    }


}