package com.eventbooking.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendSimpleMailMessage(String subject, String to, String text) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject(subject);
            mailMessage.setFrom(fromEmail);
            mailMessage.setTo(to);
            mailMessage.setText(text);
            emailSender.send(mailMessage);
        }catch (Exception e){
            System.err.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
