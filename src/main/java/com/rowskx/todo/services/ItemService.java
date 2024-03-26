package com.rowskx.todo.services;

import java.util.List;

import com.rowskx.todo.DTOs.Item.ItemDTO;
import com.rowskx.todo.models.Item;

public interface ItemService {

    Boolean add(Long taskId, ItemDTO newItem);

    void update(ItemDTO newItem);

    void delete(Long itemId, Long taskId);

    Item findById(Long itemId);

    ItemDTO findByIdItemDTO(Long itemId);

    List<ItemDTO> findAllByTaskId(Long taskId);

}
