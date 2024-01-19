package com.rowskx.todo.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rowskx.todo.repositories.ListRepository;
import com.rowskx.todo.services.ListService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ListServiceImpl implements ListService {

    @Autowired
    ListRepository listRepository;

    @Override
    public void add(String header, String userLogin) {
        listRepository.addList(header, userLogin);
    }

    @Override
    public void update(Long userId, String header) {

    }

    @Override
    public void delete(Long listId) {

    }

}
