package com.rowskx.todo.repositories;

import com.rowskx.todo.models.TeamList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamListRepository extends JpaRepository<TeamList, Long> {
}
