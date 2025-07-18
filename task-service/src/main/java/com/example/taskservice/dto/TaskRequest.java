package com.example.taskservice.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public record TaskRequest
    (
        @NotBlank String assignee,
        @NotBlank String taskName,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {

}
