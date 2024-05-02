package com.eventbooking.events.controllers;

import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.services.CustomerService;
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
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("createAccount")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest accountRequest) throws UserException {
        return new ResponseEntity<>(customerService.createAccount(accountRequest), HttpStatus.CREATED);
    }

    @PostMapping("addEvent")
    public ResponseEntity<AddEventResponse> addEvent(@RequestBody AddEventRequest addEventRequest) throws UserException, EventExistException {
      return new ResponseEntity<>(customerService.createEvent(addEventRequest), HttpStatus.CREATED);
    }
}
