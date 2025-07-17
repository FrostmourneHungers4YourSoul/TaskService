package com.example.taskservice.dto;

import java.time.LocalDateTime;

public record TaskResponse
    (
        Long id,
        String taskName,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate
    ) {

}
