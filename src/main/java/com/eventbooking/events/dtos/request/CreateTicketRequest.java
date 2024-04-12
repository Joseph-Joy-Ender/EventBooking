package com.eventbooking.events.dtos.request;

import com.eventbooking.events.data.model.TicketCategory;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class CreateTicketRequest {
    private Long id;
    private TicketCategory category;
    private BigDecimal price;
//    private LocalDate date;
    private Long eventId;

}
