package com.rowskx.todo.services;

public interface ListService {
    void add(String header, String userLogin);

    void update(Long userId, String header);

    void delete(Long listId);
}
