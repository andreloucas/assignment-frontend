package gr.assignment.frontend.service;

import gr.assignment.frontend.dto.NotificationLogDto;

import java.util.List;

public interface NotificationLogService {

    void createEmail(String emailFrom, String subject, String message, String recipient);

    List<NotificationLogDto> findAllByOrderBySendDateDesc();

    void deleteEmail(Long emailId);


}
