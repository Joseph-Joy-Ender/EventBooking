package com.eventbooking.events.dtos.response;

import com.eventbooking.events.data.model.TicketCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TicketResponse {
    private Long id;
    private TicketCategory category;
    private BigDecimal price;
    private String eventName;
}
