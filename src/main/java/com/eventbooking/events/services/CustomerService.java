package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.dtos.request.AddEventRequest;
import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.AddEventResponse;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.EventExistException;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException;
//    List<Ticket> searchTicketBy(String email, String eventName) throws TicketException;
    AddEventResponse createEvent(AddEventRequest request) throws EventExistException, UserException;
    Customer findUserBy(String email) throws UserException;
    Customer findById(Long userId) throws UserException;

}
