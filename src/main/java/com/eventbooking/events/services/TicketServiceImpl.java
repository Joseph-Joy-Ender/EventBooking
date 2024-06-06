package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.TicketStatus;
import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.data.repositories.TicketRepository;
import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.request.ReserveTicketRequest;
import com.eventbooking.events.dtos.response.BookTicketResponse;
import com.eventbooking.events.dtos.response.CancelReservedTicketResponse;
import com.eventbooking.events.dtos.response.ReserveTicketResponse;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.utils.GenerateApiResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService{

    private final EventService eventService;
    private final ModelMapper mapper = new ModelMapper();
    private final CustomerService customerService;
    private final TicketRepository ticketRepository;
    private final EmailService emailService;



    @Override
    public TicketResponse createTicket(CreateTicketRequest request) throws EventExistException {
        Event event = eventService.findEventBy(request.getEventId());
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
    public List<Ticket> searchTicketBy(String email, String eventName) throws UserException, TicketException {
        Customer customer = customerService.findUserBy(email);
        if (customer != null) return searchTicketBy(eventName);
        throw new UserException(GenerateApiResponse.USER_NOT_FOUND);
    }

    @Override
    public ReserveTicketResponse reserveTicket(ReserveTicketRequest request) throws UserException {
        Optional<Ticket> ticket = ticketRepository.findById(request.getTicketId());
        Customer customer = customerService.findById(request.getUserId());
        Ticket foundTicket = ticket.get();
        foundTicket.setReservationId(request.getReservationId());
        foundTicket.setTicketStatus(TicketStatus.RESERVED);
        foundTicket.setCustomer(customer);
        ticketRepository.save(foundTicket);
        ReserveTicketResponse response = new ReserveTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_RESERVED_SUCCESSFULLY);
        return response;
    }

    @Override
    public Customer findBy(Long id) {
        return customerService.findById(id);
    }

    @Override
    public CancelReservedTicketResponse cancelReservedTicket(Long reservationId) throws TicketException {
        Optional<Ticket> ticket = ticketRepository.findTicketByReservationId(reservationId);
        if (ticket.isEmpty()) throw new TicketException(GenerateApiResponse.TICKET_NOT_FOUND);
        Ticket foundTicket = ticket.get();
        foundTicket.setTicketStatus(TicketStatus.CANCELLED);
        ticketRepository.save(foundTicket);
        CancelReservedTicketResponse response = new CancelReservedTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_RESERVED_CANCELLED);
        return response;
    }

    @Override
    public BookTicketResponse bookTicket(Long reservationId) {
        Optional<Ticket> ticket = ticketRepository.findTicketByReservationId(reservationId);
        if (ticket.isPresent()){
            Ticket foundTicket = ticket.get();
            if (foundTicket.getTicketStatus() == TicketStatus.RESERVED){
                foundTicket.setTicketStatus(TicketStatus.BOOKED);
                ticketRepository.save(foundTicket);
            }
        }
        BookTicketResponse response = new BookTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_BOOKED_SUCCESSFULLY);

        return response;
    }

     /*
        TODO
        first check if the ticket is reserved
        second if the ticket is reserved, retrieve the ticket from the database
        third check if the reserved ticket is still available for booking
        process payment
        update ticket status to booked
        send email to user to confirm booking
        */

}
