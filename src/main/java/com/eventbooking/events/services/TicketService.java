package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.ReserveTicketResponse;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;

import java.util.List;

public interface TicketService {
    TicketResponse createTicket(Long eventId, CreateTicketRequest request) throws EventExistException;

    List<Ticket> searchTicketBy(String eventName) throws TicketException;

    ReserveTicketResponse reserveTicket(ReserveTicketRequest request) throws UserException;

    List<Ticket> searchTicketBy(String email, String eventName) throws TicketException, UserException;
}
