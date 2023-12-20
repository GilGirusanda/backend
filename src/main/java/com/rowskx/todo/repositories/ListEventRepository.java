package com.rowskx.todo.repositories;

import com.rowskx.todo.models.ListEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListEventRepository extends JpaRepository<ListEvent, Long> {
}
