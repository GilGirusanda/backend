package com.rowskx.todo.DTOs;

import java.util.List;

public record JwtResponse(String jwt, Long id, String name, String login, List<String> roles) {

}
