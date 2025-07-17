package com.example.taskservice.controller;

import com.example.taskservice.dto.MessageResponse;
import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody @Valid TaskRequest request) {
        log.info("Create task request: {}", request);
        ResponseEntity<TaskResponse> response =
            ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request));
        log.info("Create task response: {}", response);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getTasks() {
        log.info("Get tasks request");
        ResponseEntity<List<TaskResponse>> response =
            ResponseEntity.ok(taskService.getTasks());
        log.info("Get tasks response: {}", response);
        return response;
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long taskId) {
        log.info("Get tasks by id request: {}", taskId);
        ResponseEntity<TaskResponse> response =
            ResponseEntity.ok(taskService.getTask(taskId));
        log.info("Get task by id response: {}", response);
        return response;
    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long taskId,
        @RequestBody TaskRequest request) {
        log.info("Update task request: taskId{{}}, {}", taskId, request);
        ResponseEntity<TaskResponse> response =
            ResponseEntity.ok(taskService.updateTask(taskId, request));
        log.info("Update task response: {}", response);
        return response;
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long taskId) {
        log.info("Delete task request: {}", taskId);
        ResponseEntity<MessageResponse> response =
            ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(taskService.deleteTask(taskId));
        log.info("Delete task response: {}", response);
        return response;
    }

    @GetMapping("/get")
    public ResponseEntity<Void> getObjects() {
        return ResponseEntity.ok(taskService.getRequest());
    }
}
