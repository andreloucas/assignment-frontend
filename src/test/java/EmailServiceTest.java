import gr.assignment.frontend.service.impl.EmailServiceImpl;
import gr.assignment.frontend.service.impl.NotificationLogServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private TemplateEngine templateEngine;

    @Mock
    private NotificationLogServiceImpl notificationLogService;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendHtmlEmail() throws Exception {
        String subject = "Test Subject";
        String message = "This is a test message";
        String recipient = "recipient@example.com";
        String htmlContent = "<html><body>This is a test email</body></html>";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        MimeMessageHelper mimeMessageHelper = mock(MimeMessageHelper.class);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq("email/emailTemplate"), any(Context.class))).thenReturn(htmlContent);

        emailService.sendHtmlEmail(subject, message, recipient);

        verify(javaMailSender, times(1)).send(mimeMessage);
        verify(templateEngine, times(1)).process(eq("email/emailTemplate"), any(Context.class));
        verify(notificationLogService, times(1)).createEmail("andreloucas1987@gmail.com", subject, message, recipient);
    }
}
