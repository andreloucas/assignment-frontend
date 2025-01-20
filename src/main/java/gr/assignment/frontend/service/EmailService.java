package gr.assignment.frontend.service;

import jakarta.mail.MessagingException;

public interface EmailService {

    void sendHtmlEmail(String subjectProvided, String messageProvided, String recipient) throws MessagingException;
}
