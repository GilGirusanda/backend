package com.rowskx.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rowskx.todo.DTOs.ListAddRecord;
import com.rowskx.todo.DTOs.UserRetrieveRecord;
import com.rowskx.todo.models.User;
import com.rowskx.todo.services.ListService;
import com.rowskx.todo.services.serviceImpl.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<?> postMethodName(@RequestBody ListAddRecord newList) {
        // Get the authentication object from the SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the authentication object is not null and is authenticated
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

}
