package com.eventbooking.events.dtos.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AddEventResponse {
    private Long id;
    private String eventName;
    private LocalDate date;

}
