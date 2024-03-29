package com.rowskx.todo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.rowskx.todo.DTOs.List.ListAddRecord;
import com.rowskx.todo.DTOs.List.ListDeleteRecord;
import com.rowskx.todo.DTOs.List.ListEntityRecord;
import com.rowskx.todo.DTOs.List.ListReadRecord;
import com.rowskx.todo.DTOs.List.ListUpdateRecord;
import com.rowskx.todo.models.ListEntity;
import com.rowskx.todo.services.ListService;
import com.rowskx.todo.services.serviceImpl.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class ListController {

    @Autowired
    ListService listService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> createList(@RequestBody ListAddRecord newList) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Retrieve user details from the authentication object
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Extract user information (such as username)
            String userLogin = userDetails.getUsername();

            listService.add(newList.header(), userLogin);

            return ResponseEntity.ok("New user list has been added");
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/read")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> readList(@RequestBody ListReadRecord newList) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            ListEntity list = listService.findById(newList.list_id());
            ListEntityRecord listDTO = new ListEntityRecord(list.getId(), list.getHeader());

            return new ResponseEntity<>(Map.of("list", listDTO), HttpStatus.OK);
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity<>("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/read-all")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> readAllList() {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            List<ListEntity> lists = listService.findAll();

            List<ListEntityRecord> listsDTO = lists.stream()
                    .map((l) -> new ListEntityRecord(l.getId(), l.getHeader()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(Map.of("lists", listsDTO), HttpStatus.OK);
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity<>("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateList(@RequestBody ListUpdateRecord newList) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            listService.update(newList.list_id(), newList.header());

            return ResponseEntity.ok("User list has been updated");
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteList(@RequestBody ListDeleteRecord listToDelete) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and the USER is authenticated
        if (authentication != null && authentication.isAuthenticated()) {

            listService.delete(listToDelete.list_id());

            return ResponseEntity.ok("User list has been deleted");
        } else {
            // Handle the case when user is not authenticated
            return new ResponseEntity("User not authenticated", HttpStatus.FORBIDDEN);
        }
    }

}
