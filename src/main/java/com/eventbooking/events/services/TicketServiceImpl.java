package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.repositories.TicketRepository;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final EventService eventService;

    private final ModelMapper mapper = new ModelMapper();

    private final TicketRepository ticketRepository;
    @Override
    public TicketResponse createTicket(Long eventId, CreateTicketRequest request) throws EventExistException {
        Event event = eventService.findEventBy(eventId);
        Ticket ticket = new Ticket();
        ticket.setEventName(event.getEventName());
        ticket.setEventDate(event.getDate());
        ticket.setCategory(request.getCategory());
        ticket.setPrice(request.getPrice());

        Ticket savedTicket =  ticketRepository.save(ticket);

        return mapper.map(savedTicket, TicketResponse.class);
    }

    @Override
    public List<Ticket> searchTicketBy(String eventName) throws TicketException {
        List<Ticket> tickets = ticketRepository.findTicketByEventName(eventName);
        if (tickets.isEmpty()) throw new TicketException(GenerateApiResponse.TICKET_NOT_FOUND);
        return tickets;
    }
}
