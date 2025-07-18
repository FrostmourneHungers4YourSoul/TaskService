package com.example.taskservice.service.mail;

import com.example.taskservice.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendMessage(String to, TaskResponse task) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject("New Task: " + task.taskName());
        message.setText(buildMessage(task));

        mailSender.send(message);
    }

    private String buildMessage(TaskResponse task) {
        return "Task:\n"
            + "ID: " + task.id() + "\n"
            + "Title: " + task.taskName() + "\n"
            + "Description: " + task.description() + "\n"
            + "Start: " + task.startDate() + "\n"
            + "End: " + task.endDate() + "\n";
    }
}
