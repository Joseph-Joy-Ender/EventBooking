package com.eventbooking.events.controllers;

import com.eventbooking.events.dtos.request.CreateTicketRequest;
import com.eventbooking.events.dtos.response.TicketResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/eventBooking/")
@CrossOrigin("*")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("createTicket/{eventId}")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable Long eventId, @RequestBody CreateTicketRequest request) throws EventExistException {
        return new ResponseEntity<>(ticketService.createTicket(eventId, request), HttpStatus.CREATED);
    }
}
