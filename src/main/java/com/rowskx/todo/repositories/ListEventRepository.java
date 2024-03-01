package com.rowskx.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.models.ListEvent;

@Repository
public interface ListEventRepository extends JpaRepository<ListEvent, Long> {
}
