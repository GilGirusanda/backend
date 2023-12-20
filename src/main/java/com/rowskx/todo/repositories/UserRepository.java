package com.rowskx.todo.repositories;

import com.rowskx.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.login LIKE %:login%")
    List<User> findByLoginLike(@Param("login") String login);

    List<User> findAllByName(String name);

    Boolean existsByName(String name);

    Boolean existsByLogin(String login);

}
