package com.eventbooking.events.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveTicketRequest {
    private Long userId;
    private Long ticketId;
    private Long reservationId;
}
