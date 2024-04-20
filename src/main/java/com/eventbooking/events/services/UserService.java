package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.data.model.User;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException;

    AddEventResponse createEvent(AddEventRequest request) throws EventExistException, UserException;
    List<Ticket> searchTicketBy(String email, String eventName) throws TicketException, UserException;

    User findById(Long userId) throws UserException;
}
