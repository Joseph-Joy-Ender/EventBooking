package com.eventbooking.events.services;

import com.eventbooking.events.data.model.Customer;
import com.eventbooking.events.dtos.request.InitializeTransactionRequest;
import com.eventbooking.events.dtos.response.ApiResponse;
import com.eventbooking.events.dtos.response.PaystackTransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final TicketService ticketService;

    @Override
    public ApiResponse<?> makePaymentFor(Long ticketId) {
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = ticketService.findBy(ticketId);
        HttpEntity<InitializeTransactionRequest> request = buildPaymentRequest(customer);
//        ResponseEntity<PaystackTransactionResponse> response
//                = restTemplate.postForEntity("https://api.paystack.co/transaction/initialize", request, PaystackTransactionResponse.class);
//        return new ApiResponse<>(response.getBody());
        return null;
    }

    private HttpEntity<InitializeTransactionRequest> buildPaymentRequest(Customer customer) {
        InitializeTransactionRequest transactionRequest = new InitializeTransactionRequest();
        transactionRequest.setEmail(customer.getEmail());
        transactionRequest.setAmount("2000");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set(HttpHeaders.AUTHORIZATION, "Bearer "+"sk_test_56204d238cfe4397081fdfdd88e36830b221757c");
        return new HttpEntity<>(transactionRequest, headers);
    }
}
