package com.eventbooking.events.services;

import com.eventbooking.events.dtos.response.ApiResponse;
import com.eventbooking.events.exceptions.TicketException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class PaymentServiceImplTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    public void testProcessPayment() throws TicketException {
        ApiResponse<?> response = paymentService.makePaymentFor(4L, 21L);
        log.info("res-->{}", response);
        assertThat(response).isNotNull();
    }

}