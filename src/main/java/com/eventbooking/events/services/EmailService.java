package com.eventbooking.events.services;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Map;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String token);
    void sendEmailFromTemplate(String to, String subject, Map<String, String> placeholders) throws MessagingException, IOException;

}
