package com.eventbooking.events.services;

import com.eventbooking.events.dtos.response.ApiResponse;
import com.eventbooking.events.exceptions.TicketException;

public interface PaymentService {
    ApiResponse<?> makePaymentFor(Long customerId, Long ticketId) throws TicketException;
}
