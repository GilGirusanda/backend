package com.rowskx.todo.controllers;

import com.rowskx.todo.DTOs.JwtResponse;
import com.rowskx.todo.DTOs.UserLoginRecord;
import com.rowskx.todo.DTOs.UserRecord;
import com.rowskx.todo.jwt.JwtUtils;
import com.rowskx.todo.models.User;
import com.rowskx.todo.services.UserService;
import com.rowskx.todo.services.serviceImpl.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // , allowCredentials = "true", allowedHeaders = "true"
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRecord user) {
        log.info("New user {}\nwith role {} trying to register", user, user.role());

        User u = userService.findByLogin(user.login());
        if (!Objects.isNull(u)) {
            log.info("Such a user already exists {}", user.login());
            return ResponseEntity.badRequest().build();
        }

        userService.add(new User(null, user.login(), user.name(), user.password(), user.role()));

        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRecord user) {
        User u = userService.findByLogin(user.login());
        if (Objects.isNull(u)) {
            log.info("Such a user doesn't exist {}", user.login());
            return ResponseEntity.badRequest().build();
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.login(), user.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("User has logged in {}", user.login());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @GetMapping("/s-id")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@RequestParam Long id) {
        log.info("id: {}", id);
        User u = userService.findById(id);
        log.info("u: {}", u);

        if (!Objects.isNull(u)) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/s-login")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserByLogin(@RequestParam String login) {
        User u = userService.findByLogin(login);

        if (!Objects.isNull(u)) {
            return ResponseEntity.ok(u);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{login}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> searchUsersByLogin(@PathVariable("login") String login) {
        List<User> userList = userService.findByLoginLike(login);

        if (!userList.isEmpty()) {
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
