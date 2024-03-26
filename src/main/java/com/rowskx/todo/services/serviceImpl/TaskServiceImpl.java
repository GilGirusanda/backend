package com.rowskx.todo.services.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rowskx.todo.DTOs.Task.TaskAddRecord;
import com.rowskx.todo.DTOs.Task.TaskFastDTO;
import com.rowskx.todo.models.Task;
import com.rowskx.todo.repositories.TaskRepository;
import com.rowskx.todo.repositories.UserRepository;
import com.rowskx.todo.services.TaskService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public void add(Long listId, TaskFastDTO newTask) {
        taskRepository.addTask(listId, newTask.getHeader(), newTask.getContent(), newTask.getDate_time(),
                newTask.getReminder());
    }

    @Override
    public void update(TaskFastDTO newTask) {

        if (Objects.isNull(newTask.getId())) {
            log.info("Can't update the task: taskId is null");
            return;
        }

        taskRepository.findById(newTask.getId())
                .ifPresent((task) -> {
                    taskRepository.update(newTask.getId(), newTask.getHeader(), newTask.getContent(),
                            newTask.getDate_time(), newTask.getReminder());
                });
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.delete(taskId);
    }

    @Override
    public TaskFastDTO findById(Long taskId) {
        Task foundTask = null;
        try {
            foundTask = taskRepository.findById(taskId).orElseThrow();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
        return new TaskFastDTO(foundTask.getId(), foundTask.getHeader(), foundTask.getContent(),
                foundTask.getDateTime(), foundTask.getReminder());
    }

    @Override
    public List<TaskFastDTO> findAll(Long listId) {
        return taskRepository.findAll().stream()
                .filter(t->!t.getId().equals(listId))
                .map(t->{
            return new TaskFastDTO(t.getId(), t.getHeader(), t.getContent(), t.getDateTime(), t.getReminder());
        }).collect(Collectors.toList());
//        Optional<List<TaskFastDTO>> tasksOptional = Optional.ofNullable(taskRepository.findAllByListId(listId));
//
//        if (tasksOptional.isPresent())
//            return tasksOptional.get();
//        else
//            return new ArrayList<TaskFastDTO>();
    }

    @Override
    public Task findByIdTask(Long taskId) {
        Task foundTask = null;
        try {
            foundTask = taskRepository.findById(taskId).orElseThrow();
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
            return null;
        }
        return foundTask;
    }

}
