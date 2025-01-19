package gr.assignment.frontend.service.impl;

import gr.assignment.frontend.service.EmailService;
import gr.assignment.frontend.service.NotificationLogService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final NotificationLogService notificationLogService;

    @Override
    public void sendHtmlEmail(String emailFrom, String subject, String message, String recipient) throws MessagingException {
        Context context = new Context();
        context.setVariable("emailFrom", emailFrom);
        context.setVariable("subject", subject);
        context.setVariable("message", message);

        String htmlContent = templateEngine.process("email/emailTemplate", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(htmlContent, true);
        helper.setTo(recipient);
        helper.setSubject(subject);
        helper.setFrom(emailFrom);

        javaMailSender.send(mimeMessage);
        notificationLogService.createEmail(emailFrom, subject, message, recipient);
    }
}
