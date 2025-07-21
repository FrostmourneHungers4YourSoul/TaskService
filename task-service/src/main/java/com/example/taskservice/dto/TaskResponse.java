package com.example.taskservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record TaskResponse
    (
        Long id,
        String taskName,
        String description,
        @JsonFormat(pattern = "MMM dd HH:mm", locale = "en")
        LocalDateTime startDate,
        @JsonFormat(pattern = "MMM dd HH:mm", locale = "en")
        LocalDateTime endDate
    ) {

}
