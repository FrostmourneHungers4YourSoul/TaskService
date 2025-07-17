package com.example.taskservice.service.impl;

import com.example.taskservice.dto.MessageResponse;
import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.exception.ResourceAlreadyExistsException;
import com.example.taskservice.exception.ResourceNotFoundException;
import com.example.taskservice.mapper.TaskMapper;
import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;
import com.example.taskservice.service.TaskService;
import com.example.taskservice.service.mail.EmailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;
    private final RestTemplate restTemplate;
    private final EmailService emailService;

    @Override
    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        if (repository.existsByTaskName(request.taskName())) {
            throw new ResourceAlreadyExistsException(
                "Task with name {" + request.taskName() + "} already exists.");
        }
        Task task = mapper.toEntity(request);

        repository.save(task);
        log.info("Created task: {}", task);
        TaskResponse response = mapper.toDto(task);
        emailService.sendMessage(request.assignee(), response);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponse getTask(Long taskId) {
        Task task = getTaskById(taskId);
        log.info("Found task: {}", task);
        return mapper.toDto(task);
    }

    @Override
    @Cacheable(value = "tasks")
    @Transactional(readOnly = true)
    public List<TaskResponse> getTasks() {
        return repository.findAll().stream()
            .map(mapper::toDto)
            .toList();
    }

    @Override
    @Transactional
    public TaskResponse updateTask(Long taskId, TaskRequest request) {
        Task task = getTaskById(taskId);

        mapper.updateTaskFromRequest(request, task);

        task = repository.save(task);
        log.info("Task has been updated: {}", task);
        return mapper.toDto(task);
    }

    @Override
    @Transactional
    public MessageResponse deleteTask(Long taskId) {
        Task task = getTaskById(taskId);

        repository.delete(task);
        log.info("Task has been removed: {}", task);
        return new MessageResponse(String.format("Task %s has been removed.",
            task.getTaskName()));
    }

    @Override
    public Void getRequest() {
        log.info("Response: GET https://api.restful-api.dev/objects");
        Object objects = restTemplate.getForObject("https://api.restful-api.dev/objects", Object.class);
        log.info("Result: {}", objects);
        return null;
    }

    private Task getTaskById(Long taskId) {
        return repository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Task with id {" + taskId + "} not found"));
    }
}
