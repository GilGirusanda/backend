package com.rowskx.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.models.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
