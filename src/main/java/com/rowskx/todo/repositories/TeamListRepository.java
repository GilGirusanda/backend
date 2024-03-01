package com.rowskx.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.models.TeamList;

@Repository
public interface TeamListRepository extends JpaRepository<TeamList, Long> {
}
