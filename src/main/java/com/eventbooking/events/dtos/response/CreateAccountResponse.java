package com.eventbooking.events.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Setter
@Getter
public class CreateAccountResponse {
    private HttpStatus httpStatus;
    private boolean isSuccessful;
    private Object data;

}
