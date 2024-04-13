package com.eventbooking.events.services;

import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;

public interface TicketService {
    TicketResponse createTicket(Long eventId, CreateTicketRequest request) throws EventExistException;
}
