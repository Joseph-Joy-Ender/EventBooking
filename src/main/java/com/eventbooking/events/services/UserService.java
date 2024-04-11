package com.eventbooking.events.services;

import com.eventbooking.events.dtos.request.CreateAccountRequest;
import com.eventbooking.events.dtos.response.CreateAccountResponse;
import com.eventbooking.events.exceptions.UserException;

public interface UserService {
    CreateAccountResponse createAccount(CreateAccountRequest accountRequest) throws UserException;
}
