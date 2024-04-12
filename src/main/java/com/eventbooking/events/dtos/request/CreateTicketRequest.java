package com.eventbooking.events.dtos.request;

import com.eventbooking.events.data.model.Event;
import com.eventbooking.events.data.model.TicketCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
@Setter
@Getter
public class CreateTicketRequest {

    private Long eventId;
    private TicketCategory category;
    private BigDecimal price;
    private LocalDate date;
    private Event event;
}
