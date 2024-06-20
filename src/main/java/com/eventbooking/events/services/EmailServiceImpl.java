package com.eventbooking.events.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import static org.antlr.v4.runtime.misc.Utils.readFile;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Autowired
    private ResourceLoader resourceLoader;


    public void sendEmailFromTemplate(String to, String subject, Map<String, String> placeholders) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        Resource resource = resourceLoader.getResource("classpath:template.html");
        String htmlTemplate = new String(Files.readAllBytes(Paths.get(resource.getURI())));

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            htmlTemplate = htmlTemplate.replace("${" + entry.getKey() + "}", entry.getValue());
        }

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlTemplate, true);

        emailSender.send(message);
    }




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

    public void sendEmailFromTemplate() throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(new InternetAddress("sender@example.com"));
        message.setRecipients(MimeMessage.RecipientType.TO, "recipient@example.com");
        message.setSubject("Test email from my Springapplication");

        // Read the HTML template into a String variable
        String htmlTemplate = Arrays.toString(readFile("template.html"));

        // Replace placeholders in the HTML template with dynamic values
        htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
        htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        emailSender.send(message);
    }

    public void sendHtmlEmail() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(MimeMessage.RecipientType.TO, "udeme5017@gmail.com");
        message.setSubject("Test email from Spring");

        String htmlContent = "<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";
        message.setContent(htmlContent, "text/html; charset=utf-8");

        emailSender.send(message);
    }


}
