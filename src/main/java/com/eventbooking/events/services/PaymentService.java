package com.eventbooking.events.services;

import com.eventbooking.events.dtos.response.ApiResponse;

public interface PaymentService {
    ApiResponse<?> makePaymentFor(Long ticketId);
}
