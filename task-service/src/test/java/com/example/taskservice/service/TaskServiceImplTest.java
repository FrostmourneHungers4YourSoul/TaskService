package com.example.taskservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.taskservice.dto.TaskRequest;
import com.example.taskservice.dto.TaskResponse;
import com.example.taskservice.mapper.TaskMapper;
import com.example.taskservice.model.Task;
import com.example.taskservice.repository.TaskRepository;
import com.example.taskservice.service.impl.TaskServiceImpl;
import com.example.taskservice.service.mail.EmailService;
import com.example.taskservice.utils.TaskUtil;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper mapper;
    @Mock
    private EmailService emailService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    @DisplayName("Создание задачи")
    void testSuccessfullyCreateTask() {
        //given
        TaskRequest request = TaskUtil.getTaskRequest();

        when(mapper.toEntity(any(TaskRequest.class))).thenReturn(TaskUtil.getTaskTransient());
        when(taskRepository.save(any(Task.class))).thenReturn(TaskUtil.getTaskPersist());
        when(mapper.toDto(any(Task.class))).thenReturn(TaskUtil.getTaskResponse());

        //when
        TaskResponse result = taskService.createTask(request);

        //then
        assertNotNull(result);
        assertEquals(111L, result.id());

        verify(mapper).toEntity(any(TaskRequest.class));
        verify(taskRepository).save(any(Task.class));
        verify(mapper).toDto(any(Task.class));
        verify(emailService, times(1))
            .sendMessage(any(String.class), any(TaskResponse.class));
    }

    @Test
    @DisplayName("Получение задачи по ID")
    void testSuccessfullyGetTaskById() {
        //given
        Long id = 111L;

        when(taskRepository.findById(anyLong())).thenReturn(
            Optional.ofNullable(TaskUtil.getTaskPersist()));
        when(mapper.toDto(any(Task.class))).thenReturn(TaskUtil.getTaskResponse());

        //when
        TaskResponse result = taskService.getTask(id);

        //then
        assertNotNull(result);

        verify(taskRepository, times(1)).findById(anyLong());
        verify(mapper).toDto(any(Task.class));
    }

    @Test
    @DisplayName("Получение списка всех задач")
    void testSuccessfullyGetTasks() {
        //given
        when(taskRepository.findAll())
            .thenReturn(Collections.singletonList(TaskUtil.getTaskPersist()));
        when(mapper.toDto(any(Task.class))).thenReturn(TaskUtil.getTaskResponse());

        //when
        List<TaskResponse> result = taskService.getTasks();

        //then
        assertEquals(1, result.size());

        verify(taskRepository).findAll();
        verify(mapper, times(1)).toDto(any(Task.class));
    }

    @Test
    @DisplayName("Обновление задачи")
    void testSuccessfullyUpdateTask() {
        //given
        Long id = 111L;
        TaskRequest updateRequest = TaskUtil.getTaskUpdateRequest();

        when(taskRepository.findById(anyLong()))
            .thenReturn(Optional.ofNullable(TaskUtil.getTaskPersist()));
        when(taskRepository.save(any(Task.class))).thenReturn(TaskUtil.getUpdatedTask());
        when(mapper.toDto(any(Task.class))).thenReturn(TaskUtil.getTaskUpdatedResponse());

        //when
        TaskResponse result = taskService.updateTask(id, updateRequest);

        //then
        assertNotNull(result);
        assertEquals(id, result.id());

        verify(taskRepository, times(1)).findById(anyLong());
        verify(taskRepository, times(1)).save(any(Task.class));
        verify(mapper, times(1)).toDto(any(Task.class));
    }

    @Test
    @DisplayName("Удаление задачи")
    void testDeleteTaskByIdWhenFound() {
        //given
        Long id = 111L;
        when(taskRepository.findById(anyLong()))
            .thenReturn(Optional.ofNullable(TaskUtil.getTaskPersist()));

        //when
        taskService.deleteTask(id);

        //then
        verify(taskRepository, times(1)).delete(any(Task.class));
    }
}
