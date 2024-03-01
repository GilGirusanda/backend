package com.rowskx.todo.services;

import java.time.LocalDateTime;
import java.util.List;

import com.rowskx.todo.DTOs.Task.TaskFastDTO;
import com.rowskx.todo.models.Task;

public interface TaskService {
    void add(Long listId, TaskFastDTO newTask);

    void update(TaskFastDTO newTask);

    void delete(Long taskId);

    TaskFastDTO findById(Long taskId);

    Task findByIdTask(Long taskId);

    List<TaskFastDTO> findAll(Long listId);
}
