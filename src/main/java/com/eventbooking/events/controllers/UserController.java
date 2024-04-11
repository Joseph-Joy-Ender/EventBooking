package com.eventbooking.events.controllers;

import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.UserException;
import com.eventbooking.events.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/eventBooking/")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("createAccount")
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest accountRequest) throws UserException {
        return new ResponseEntity<>(userService.createAccount(accountRequest), HttpStatus.CREATED);
    }
}
