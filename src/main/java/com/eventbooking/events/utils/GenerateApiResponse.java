package com.eventbooking.events.utils;

import com.eventbooking.events.dtos.response.CreateAccountResponse;
import org.springframework.http.HttpStatus;

public class GenerateApiResponse {
    public static final String ACCOUNT_WITH_THIS_EMAIL_ALREADY_EXIST = "User with this email address already exist";
    public static final String REGISTER_SUCCESSFUL = "User registered successfully";

    public static CreateAccountResponse create(Object data){
        return CreateAccountResponse.builder()
                .data(data)
                .httpStatus(HttpStatus.CREATED)
                .isSuccessful(true)
                .build();
    }
}
