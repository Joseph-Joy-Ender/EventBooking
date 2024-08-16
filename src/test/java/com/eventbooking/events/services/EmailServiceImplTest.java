package com.eventbooking.events.services;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendEmailFromTemplate() throws MessagingException, IOException {
        Map<String, Object> placeholders = new HashMap<>();
        placeholders.put("name", "Cephas");
        placeholders.put("EventName", "Burna Boy Concert");
        placeholders.put("EventDate", "10-12-2004");
        placeholders.put("PRICE", "20000");
        placeholders.put("Status", "Booked");
        String to = "udeme5017@gmail.com";
        emailService.sendEmailFromTemplate(to, placeholders);
        assertThat("udeme5017@gmail.com").isEqualTo(to);
    }
}