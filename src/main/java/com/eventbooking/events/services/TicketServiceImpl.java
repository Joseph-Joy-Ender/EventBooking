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
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eventbooking.events.data.model.TicketStatus.BOOKED;
import static com.eventbooking.events.data.model.TicketStatus.RESERVED;

@Service
@AllArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService{

    private final EventService eventService;
    private final ModelMapper mapper = new ModelMapper();
    private final CustomerService customerService;
    private final TicketRepository ticketRepository;
    private final EmailService emailService;
//    private PaymentService paymentService;


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
        if (ticket.isPresent()) {
            Ticket foundTicket = ticket.get();
            foundTicket.setReservationId(request.getReservationId());
            foundTicket.setTicketStatus(RESERVED);
            foundTicket.setCustomer(customer);
            ticketRepository.save(foundTicket);
        }
        ReserveTicketResponse response = new ReserveTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_RESERVED_SUCCESSFULLY);
        return response;
    }

    @Override
    public Ticket findBy(Long id) throws TicketException {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketException(GenerateApiResponse.TICKET_NOT_FOUND));
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
    public BookTicketResponse bookTicket(Long reservationId, Long customerId, Long ticketId) throws MessagingException, IOException, TicketException {
        Optional<Ticket> ticket = ticketRepository.findTicketByReservationId(reservationId);
        if (ticket.isPresent()){
            Ticket foundTicket = ticket.get();
            if (foundTicket.getTicketStatus().equals(BOOKED)){
                throw new TicketException(GenerateApiResponse.TICKET_ALREADY_BOOKED);
            }
            if (foundTicket.getTicketStatus().equals(RESERVED)){
                foundTicket.setTicketStatus(BOOKED);
                ticketRepository.save(foundTicket);
//                paymentService.makePaymentFor(customerId, ticketId);
            }
            emailSending(customerId, foundTicket);
        }

        BookTicketResponse response = new BookTicketResponse();
        response.setMessage(GenerateApiResponse.TICKET_BOOKED_SUCCESSFULLY);

        return response;
    }

    private void emailSending(Long id, Ticket foundTicket) throws MessagingException, IOException {
        Customer customer = customerService.findById(id);
        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("customerName", customer.getName());
        placeholders.put("eventName", foundTicket.getEventName());
        placeholders.put("eventDate", String.valueOf(foundTicket.getEventDate()));
        placeholders.put("price", String.valueOf(foundTicket.getPrice()));
        placeholders.put("ticketStatus", String.valueOf(foundTicket.getTicketStatus()));
        emailService.sendEmailFromTemplate(customer.getEmail(), placeholders);
    }
    
}

