package com.example.taskservice.utils;

import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.model.Task;
import java.time.LocalDateTime;

public class TaskUtil {

    public static Task getTaskTransient() {
        return Task.builder()
            .taskName("Test")
            .description("Unit tests for service methods")
            .startDate(LocalDateTime.parse("2025-07-18T13:00"))
            .endDate(LocalDateTime.parse("2025-07-18T14:30"))
            .build();
    }

    public static Task getTaskPersist() {
        return Task.builder()
            .id(111L)
            .taskName("Test")
            .description("Unit tests for service methods")
            .startDate(LocalDateTime.parse("2025-07-18T13:00"))
            .endDate(LocalDateTime.parse("2025-07-18T14:30"))
            .build();
    }

    public static TaskResponse getTaskResponse() {
        return new TaskResponse
            (
                111L,
                "Test",
                "Unit tests for service methods",
                LocalDateTime.parse("2025-07-18T13:00"),
                LocalDateTime.parse("2025-07-18T14:30")
            );
    }

    public static TaskRequest getTaskRequest() {
        return new TaskRequest(
            "some_email@gmail.com",
            "Test",
            "Unit tests for service methods",
            LocalDateTime.parse("2025-07-18T13:00"),
            LocalDateTime.parse("2025-07-18T14:30")
        );
    }

    public static TaskRequest getTaskUpdateRequest() {
        return new TaskRequest(
            "email2@gmail.com",
            "Test-2",
            "Push changes",
            LocalDateTime.parse("2025-07-18T14:30"),
            LocalDateTime.parse("2025-07-18T14:35")
        );
    }

    public static Task getUpdatedTask() {
        return Task.builder()
            .taskName("Test-2")
            .description("Push changes")
            .startDate(LocalDateTime.parse("2025-07-18T14:30"))
            .endDate(LocalDateTime.parse("2025-07-18T14:35"))
            .build();
    }

    public static TaskResponse getTaskUpdatedResponse() {
        return new TaskResponse
            (
                111L,
                "Test-2",
                "Push changes",
                LocalDateTime.parse("2025-07-18T14:30"),
                LocalDateTime.parse("2025-07-18T14:35")
            );
    }
}
