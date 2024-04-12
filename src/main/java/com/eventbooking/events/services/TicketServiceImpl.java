package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.repositories.TicketRepository;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final EventService eventService;

    private final TicketRepository ticketRepository;
    @Override
    public TicketResponse createTicket(CreateTicketRequest request) throws EventExistException {
//        Event event = eventService.findEventBy(request);
//        Ticket ticket = new Ticket();
//        ticket.setCategory(request.getCategory());
//        ticket.setDate(event.getDate());
//        ticket.setPrice(request.getPrice());
//        ticket.setEventName(event.getEventName());
//
//        ticketRepository.save(ticket);

        TicketResponse response = new TicketResponse();
        response.setMessage("Success");

        return response;
    }
}
