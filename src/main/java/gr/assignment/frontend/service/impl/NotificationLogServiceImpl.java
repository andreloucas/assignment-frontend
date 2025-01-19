package gr.assignment.frontend.service.impl;

import gr.assignment.frontend.dto.NotificationLogDto;
import gr.assignment.frontend.entity.NotificationLogEntity;
import gr.assignment.frontend.exceptions.NotFoundException;
import gr.assignment.frontend.repository.NotificationLogRepository;
import gr.assignment.frontend.service.NotificationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class NotificationLogServiceImpl implements NotificationLogService {
    private final NotificationLogRepository notificationLogRepository;


    @Override
    public void createEmail(String emailFrom, String subject, String message, String recipient) {
        NotificationLogEntity email = new NotificationLogEntity();
        email.setSendTo(recipient);
        email.setSubject(subject);
        email.setBody(message);
        email.setSendDate(LocalDateTime.now());
        notificationLogRepository.save(email);
    }

    @Override
    public void deleteEmail(Long emailId) {
        NotificationLogEntity email = notificationLogRepository.findById(emailId).orElse(null);
        if (email == null) {
            throw new NotFoundException();
        }
        notificationLogRepository.delete(email);
    }

    @Override
    public List<NotificationLogDto> findAllByOrderBySendDateDesc() {
        List<NotificationLogDto> dtos = new ArrayList<>();
        List<NotificationLogEntity> emails = notificationLogRepository.findAllByOrderBySendDateDesc();
        for (NotificationLogEntity email : emails) {
            dtos.add(convertToDto(email));
        }
        return dtos;
    }

    private NotificationLogDto convertToDto(NotificationLogEntity email) {
        NotificationLogDto dto = new NotificationLogDto();
        dto.setId(email.getId());
        dto.setSendTo(email.getSendTo());
        dto.setSubject(email.getSubject());
        dto.setBody(email.getBody());
        dto.setSendDate(email.getSendDate());
        return dto;
    }

}

