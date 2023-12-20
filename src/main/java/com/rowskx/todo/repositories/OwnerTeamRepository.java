package com.rowskx.todo.repositories;

import com.rowskx.todo.models.OwnerTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerTeamRepository extends JpaRepository<OwnerTeam, Long> {
}
