package com.rowskx.todo.repositories;

import com.rowskx.todo.models.ListTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListTaskRepository extends JpaRepository<ListTask, Long> {
}
