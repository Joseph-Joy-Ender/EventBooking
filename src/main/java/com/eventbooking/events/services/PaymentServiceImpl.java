package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.data.model.Ticket;
import com.eventbooking.events.dtos.request.InitializeTransactionRequest;
import com.eventbooking.events.dtos.response.ApiResponse;
import com.eventbooking.events.dtos.response.PaystackTransactionResponse;
import com.eventbooking.events.exceptions.TicketException;
import com.eventbooking.events.paymentConfig.Config;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private TicketService ticketService;
    private final CustomerService customerService;
    private final Config config;

    @Override
    public ApiResponse<?> makePaymentFor(Long customerId, Long ticketId) throws TicketException {
        RestTemplate restTemplate = new RestTemplate();
        Ticket ticket = ticketService.findBy(ticketId);
        Customer customer = customerService.findById(customerId);
        HttpEntity<InitializeTransactionRequest> request = buildPaymentRequest(customer,ticket);
        ResponseEntity<PaystackTransactionResponse> response
                = restTemplate.postForEntity(config.getPaystackBaseUrl(), request, PaystackTransactionResponse.class);
        System.out.println(Objects.requireNonNull(response.getBody()).getPaystackTransactionDetails().getAuthorizationUrl());
        return new ApiResponse<>(response.getBody());

    }

    private HttpEntity<InitializeTransactionRequest> buildPaymentRequest(Customer customer, Ticket ticket) {
        InitializeTransactionRequest transactionRequest = new InitializeTransactionRequest();
        transactionRequest.setEmail(customer.getEmail());
        transactionRequest.setAmount(String.valueOf(ticket.getPrice().multiply(BigDecimal.valueOf(100))));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+config.getPaystackApiKey());
        return new HttpEntity<>(transactionRequest, headers);
    }
}
