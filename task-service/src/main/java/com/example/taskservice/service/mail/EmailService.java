package com.example.taskservice.service.mail;

import com.example.taskservice.dto.TaskResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Async
    public void sendMessage(String to, TaskResponse task) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("Task service: " + task.taskName());
        message.setText(buildMessage(task));

        mailSender.send(message);
    }

    private String buildMessage(TaskResponse task) {
        return String.format(
            """
                =======> Task <=============
                =| ID: %s
                =| Title: %s
                =| Description:
                =| %s
                =| Deadline:
                =| %s - %s
                ===========================
                """,
            task.id(),
            task.taskName(),
            task.description(),
            parse(task.startDate()),
            parse(task.endDate())
        );
    }

    public static String parse(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd HH:mm", Locale.ENGLISH);
        return localDateTime.format(formatter);
    }
}
