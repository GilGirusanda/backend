package com.rowskx.todo.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rowskx.todo.DTOs.Item.ItemAddRecord;
import com.rowskx.todo.DTOs.Item.ItemDTO;
import com.rowskx.todo.DTOs.Task.TaskAddRecord;
import com.rowskx.todo.DTOs.Task.TaskFastDTO;
import com.rowskx.todo.services.ItemService;
import com.rowskx.todo.services.TaskService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createList(@RequestBody ItemAddRecord newItem) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            itemService.add(newItem.task_id(), new ItemDTO(null,
                    newItem.content(),
                    newItem.finish_status()));

            return ResponseEntity.ok("New item has been added");
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    // @GetMapping("/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    // public ResponseEntity<?> findTaskById(@PathVariable Long id) {
    // // Get the authentication object from the SecurityContext
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();

    // // Check if the authentication object is not null and the USER is
    // authenticated
    // if (authentication != null && authentication.isAuthenticated()) {
    // TaskFastDTO task = taskService.findById(id);
    // if (!java.util.Objects.isNull(task))
    // return ResponseEntity.ok(Map.of("task", task));
    // else
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
    // } else {
    // // Handle the case when user is not authenticated
    // return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
    // }
    // }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> readAllItemsByTaskId(@RequestParam("task_id") Long taskId) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            List<ItemDTO> items = itemService.findAllByTaskId(taskId);

            return ResponseEntity.ok(Map.of("items", items));
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    // @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    // public ResponseEntity<?> deleteTaskById(@PathVariable Long id) {
    // // Get the authentication object from the SecurityContext
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();

    // // Check if the authentication object is not null and the USER is
    // authenticated
    // if (authentication != null && authentication.isAuthenticated()) {
    // taskService.delete(id);
    // return ResponseEntity.ok(Map.of("msg", "Task has been deleted
    // successfully"));
    // } else {
    // // Handle the case when user is not authenticated
    // return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
    // }
    // }

    // @PutMapping
    // @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    // public ResponseEntity<?> updateTask(@RequestBody TaskFastDTO task) {
    // // Get the authentication object from the SecurityContext
    // Authentication authentication =
    // SecurityContextHolder.getContext().getAuthentication();

    // // Check if the authentication object is not null and the USER is
    // authenticated
    // if (authentication != null && authentication.isAuthenticated()) {
    // taskService.update(task);
    // return ResponseEntity.ok(Map.of("msg", "Task has been updated
    // successfully"));
    // } else {
    // // Handle the case when user is not authenticated
    // return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
    // }
    // }

}
