package com.rowskx.todo.DTOs.User;

import com.rowskx.todo.models.Role;

public record UserRecord(String login, String name, String password, Role role) {

}