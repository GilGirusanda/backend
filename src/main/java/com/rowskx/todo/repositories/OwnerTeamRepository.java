package com.rowskx.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.models.OwnerTeam;

@Repository
public interface OwnerTeamRepository extends JpaRepository<OwnerTeam, Long> {
}
