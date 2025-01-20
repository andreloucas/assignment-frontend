package gr.assignment.frontend.controller;

import gr.assignment.frontend.dto.NotificationLogDto;
import gr.assignment.frontend.service.EmailService;
import gr.assignment.frontend.service.NotificationLogService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmailController {
    private final EmailService emailService;
    private final NotificationLogService notificationLogService;

    @GetMapping("/emailForm")
    public String emailForm(Model model) {
        model.addAttribute("notificationLogDto", new NotificationLogDto());
        return "emailForm";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("notificationLogDto") NotificationLogDto notificationLogDto) throws MessagingException {
        emailService.sendHtmlEmail(notificationLogDto.getSubject(), notificationLogDto.getBody(), notificationLogDto.getSendTo());
        return "redirect:/emails";
    }

    @GetMapping("/emails")
    public String emails(Model model) {
        List<NotificationLogDto> emails = notificationLogService.findAllByOrderBySendDateDesc();
        model.addAttribute("emails", emails);
        return "emails";
    }

    @PostMapping("/deleteEmail/{emailId}")
    public String deleteEmail(@PathVariable Long emailId) {
        notificationLogService.deleteEmail(emailId);
        return "redirect:/emails";
    }
}

