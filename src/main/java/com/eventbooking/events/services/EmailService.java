package com.eventbooking.events.services;

public interface EmailService {
    void sendSimpleMailMessage(String name, String to, String token);

}
