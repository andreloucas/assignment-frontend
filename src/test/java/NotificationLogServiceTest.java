import gr.assignment.frontend.dto.NotificationLogDto;
import gr.assignment.frontend.entity.NotificationLogEntity;
import gr.assignment.frontend.exceptions.NotFoundException;
import gr.assignment.frontend.repository.NotificationLogRepository;
import gr.assignment.frontend.service.impl.NotificationLogServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationLogServiceTest {

    @Mock
    private NotificationLogRepository notificationLogRepository;

    @InjectMocks
    private NotificationLogServiceImpl emailLogService;

    @Test
    void testCreateEmail() {
        String emailFrom = "test@example.com";
        String subject = "testSubject";
        String message = "testMessage";
        String recipient = "recipient@test.com";

        emailLogService.createEmail(emailFrom, subject, message, recipient);
        verify(notificationLogRepository, times(1)).save(any(NotificationLogEntity.class));
    }

    @Test
    void testFindAllByOrderBySendDateDesc() {
        List<NotificationLogEntity> emails = new ArrayList<>();
        NotificationLogEntity email = new NotificationLogEntity();
        email.setId(1L);
        email.setSendTo("sendTo");
        email.setSubject("subject");
        email.setBody("body");
        email.setSendDate(LocalDateTime.now());
        emails.add(email);

        when(notificationLogRepository.findAllByOrderBySendDateDesc()).thenReturn(emails);

        List<NotificationLogDto> dtos = emailLogService.findAllByOrderBySendDateDesc();

        assertEquals(email.getId(), dtos.get(0).getId());
        assertEquals(email.getSendTo(), dtos.get(0).getSendTo());
        assertEquals(email.getSubject(), dtos.get(0).getSubject());
        assertEquals(email.getBody(), dtos.get(0).getBody());
        assertEquals(email.getSendDate(), dtos.get(0).getSendDate());
    }

    @Test
    void testDeleteEmail() {
        Long emailId = 1L;
        NotificationLogEntity email = new NotificationLogEntity();
        email.setId(emailId);

        when(notificationLogRepository.findById(emailId)).thenReturn(Optional.of(email));
        emailLogService.deleteEmail(emailId);
        verify(notificationLogRepository, times(1)).delete(any(NotificationLogEntity.class));
    }

    @Test
    void deleteEmail_nullField_throwsNotFoundException() {
        Long emailId = 1L;
        NotificationLogEntity email = new NotificationLogEntity();
        email.setId(emailId);

        when(notificationLogRepository.findById(emailId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> {
            emailLogService.deleteEmail(emailId);
        });
        verify(notificationLogRepository, never()).delete(any(NotificationLogEntity.class));
    }
}
