package com.eventbooking.events.utils;

import com.eventbooking.events.dtos.response.CreateAccountResponse;
import org.springframework.http.HttpStatus;

public class GenerateApiResponse {
    public static final String ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXIST = "User with this email address already exist";
    public static final String REGISTER_SUCCESSFUL = "User registered successfully";
    public static final String EVENT_WITH_THIS_DATE_AND_NAME_EXIST = "Event with this name and date already exist";
    public static final String USER_NOT_FOUND = "User with this email is not found";
    public static final String EVENT_DOES_NOT_EXIST = "Event does not exist";
    public static final String TICKET_NOT_FOUND = "Ticket not found";

    public static CreateAccountResponse create(Object data){
        return CreateAccountResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .isSuccessful(true)
                .build();
    }
}
