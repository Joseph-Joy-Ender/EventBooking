package com.eventbooking.events.dtos.request;

import com.eventbooking.events.data.model.TicketStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class EmailRequest {
    private String subject;
    private String customerName;
    private String customerEmail;
    private String eventName;
    private LocalDate eventDate;
    private BigDecimal price;
    private TicketStatus ticketStatus;
}
