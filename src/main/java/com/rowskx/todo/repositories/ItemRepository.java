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

    @Query(value = "SELECT new com.rowskx.todo.DTOs.Item.ItemDTO(item.id, item.content, item.finishStatus) FROM Item item WHERE item.task.id = :taskId")
    List<ItemDTO> findDtoByTaskId(@Param("taskId") Long taskId);

    @Query(value = "SELECT * FROM item WHERE item.task_id = :taskId ;", nativeQuery = true)
    List<Item> findByTaskId(@Param("taskId") Long taskId);

    @Query(value = "SELECT * FROM update_item(:itemId, :itemContent, :itemStatus) ;", nativeQuery = true)
    void update(@Param("itemId") Long itemId, @Param("itemContent") String content,
            @Param("itemStatus") Boolean finishStatus);

    @Query(value = "SELECT * FROM delete_item(:itemId, :taskId) ;", nativeQuery = true)
    void deleteItem(@Param("itemId") Long itemId, @Param("taskId") Long taskId);
}