package com.eventbooking.events.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ExceptionApiResponse extends Throwable{
    private boolean isSuccessful;
    private Object data;
}
