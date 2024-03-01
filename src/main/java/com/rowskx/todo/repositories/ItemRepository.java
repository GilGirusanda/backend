package com.rowskx.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rowskx.todo.DTOs.Item.ItemDTO;
import com.rowskx.todo.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM item WHERE item.task_id = :taskId ;", nativeQuery = true)
    List<ItemDTO> findByTaskId(@Param("taskId") Long taskId);

}