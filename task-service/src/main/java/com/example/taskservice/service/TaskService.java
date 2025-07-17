package com.example.taskservice.service;

import com.example.taskservice.dto.MessageResponse;
import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import java.util.List;

public interface TaskService {
    TaskResponse createTask(TaskRequest request);

    TaskResponse getTask(Long taskId);
    List<TaskResponse> getTasks();

    TaskResponse updateTask(Long taskId, TaskRequest request);

    MessageResponse deleteTask(Long taskId);

    Void getRequest();
}
