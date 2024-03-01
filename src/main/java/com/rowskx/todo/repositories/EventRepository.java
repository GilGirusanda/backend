package com.rowskx.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
