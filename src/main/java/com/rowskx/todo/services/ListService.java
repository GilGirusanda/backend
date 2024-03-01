package com.rowskx.todo.services;

import java.util.List;

import com.rowskx.todo.models.ListEntity;

public interface ListService {
    void add(String header, String userLogin);

    void update(Long userId, String header);

    void delete(Long listId);

    ListEntity findById(Long listId);

    List<ListEntity> findAll();
}
