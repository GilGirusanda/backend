package com.rowskx.todo.repositories;

import com.rowskx.todo.models.ListEntity;
import com.rowskx.todo.models.User;
import com.rowskx.todo.models.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserListRepository extends JpaRepository<UserList, Long> {
    UserList findByListAndUser(ListEntity list, User user);
}
