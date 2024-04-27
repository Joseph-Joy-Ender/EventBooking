package com.eventbooking.events.controllers;

import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/eventBooking/")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("createEvent")
    public ResponseEntity<AddEventResponse> createEvent(@Valid @RequestBody AddEventRequest request) throws EventExistException {
        return new ResponseEntity<>(eventService.createEvent(request), HttpStatus.CREATED);
    }
}
