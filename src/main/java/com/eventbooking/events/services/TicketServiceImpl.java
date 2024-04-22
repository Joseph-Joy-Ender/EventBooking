package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.TicketStatus;
import com.eventbooking.events.data.model.User;
import com.eventbooking.events.data.repositories.TicketRepository;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.ReserveTicketResponse;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    private EventService eventService;

    @Autowired
    private UserService userService;

    private final ModelMapper mapper = new ModelMapper();

    public void setEventService(@Autowired EventService eventService) {
        this.eventService = eventService;
    }
//    public void setUserService(@Autowired UserService userService) {
//        this.userService = userService;
//    }

    @Autowired
    private TicketRepository ticketRepository;

//    public void setTicketRepository(@Autowired TicketRepository ticketRepository) {
//        this.ticketRepository = ticketRepository;
//    }

    @Override
    public TicketResponse createTicket(Long eventId, CreateTicketRequest request) throws EventExistException {
        Event event = eventService.findEventBy(eventId);
        Ticket ticket = new Ticket();
        ticket.setEventName(event.getEventName());
        ticket.setEventDate(event.getDate());
        ticket.setCategory(request.getCategory());
        ticket.setPrice(request.getPrice());
        ticket.setTicketStatus(TicketStatus.AVAILABLE);

        Ticket savedTicket =  ticketRepository.save(ticket);

        return mapper.map(savedTicket, TicketResponse.class);
    }

    @Override
    public List<Ticket> searchTicketBy(String eventName) throws TicketException {
        List<Ticket> tickets = ticketRepository.findTicketByEventName(eventName);
        if (tickets.isEmpty()) throw new TicketException(GenerateApiResponse.TICKET_NOT_FOUND);
        return tickets;
    }

    @Override
    public ReserveTicketResponse reserveTicket(ReserveTicketRequest request) throws UserException {
        Optional<Ticket> ticket = ticketRepository.findById(request.getEventId());
        User user = userService.findById(request.getUserId());
        Ticket foundTicket = ticket.get();
        foundTicket.setTicketStatus(TicketStatus.RESERVED);
        foundTicket.setUser(user);
        ticketRepository.save(foundTicket);
        ReserveTicketResponse response = new ReserveTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_RESERVED_SUCCESSFULLY);
        return response;
    }
}
