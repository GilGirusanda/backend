package com.rowskx.todo.services;

import com.rowskx.todo.models.Role;
import com.rowskx.todo.models.User;

import java.util.List;

public interface UserService {

    void add(User newUser);

    void delete(Long id);

    void update(Long id, User newUser);

    User findById(Long id);

    List<User> findByLoginLike(String login);

    User findByLogin(String login);

    List<User> findAllByName(String name);

    List<User> findAll();

    void updateByLogin(String login, User u);

    Role saveRole(Role role);

    void setNewRoleToUser(String login, String roleName);
}
