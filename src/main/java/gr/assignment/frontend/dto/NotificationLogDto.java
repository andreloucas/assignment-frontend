package gr.assignment.frontend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationLogDto {

    private Long id;
    private String sendTo;
    private String subject;
    private String body;
    private LocalDateTime sendDate;
}
