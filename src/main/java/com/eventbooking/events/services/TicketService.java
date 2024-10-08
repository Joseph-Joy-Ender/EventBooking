package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.BookTicketResponse;
import com.eventbooking.events.dtos.response.CancelReservedTicketResponse;
import com.eventbooking.events.dtos.response.ReserveTicketResponse;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.List;

public interface TicketService {
    TicketResponse createTicket(CreateTicketRequest request) throws EventExistException;

    List<Ticket> searchTicketBy(String eventName) throws TicketException;

    List<Ticket> searchTicketBy(String email, String eventName) throws UserException, TicketException;

    ReserveTicketResponse reserveTicket(ReserveTicketRequest request) throws UserException;
    Ticket findBy(Long id) throws TicketException;

    CancelReservedTicketResponse cancelReservedTicket(Long reservationId) throws TicketException;

    BookTicketResponse bookTicket(Long reservationId, Long customerId, Long ticketId) throws MessagingException, IOException, TicketException;

}
