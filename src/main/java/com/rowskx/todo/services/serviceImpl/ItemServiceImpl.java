package com.rowskx.todo.services.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rowskx.todo.DTOs.Item.ItemDTO;
import com.rowskx.todo.DTOs.Task.TaskFastDTO;
import com.rowskx.todo.models.Item;
import com.rowskx.todo.models.Task;
import com.rowskx.todo.repositories.ItemRepository;
import com.rowskx.todo.services.ItemService;
import com.rowskx.todo.services.TaskService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TaskService taskService;

    @Override
    public void add(Long taskId, ItemDTO newItem) {
        Optional<Task> existingTask = Optional.ofNullable(taskService.findByIdTask(taskId));
        if (existingTask.isPresent())
            itemRepository.save(
                    new Item(newItem.getId(), newItem.getContent(), newItem.getFinish_status(),
                            existingTask.get()));
    }

    @Override
    public void update(ItemDTO newItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long itemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    @Override
    public ItemDTO findByIdItemDTO(Long itemId) {
        Optional<Item> existingItem = itemRepository.findById(itemId);
        if (existingItem.isPresent()) {
            Item item = existingItem.get();
            return new ItemDTO(item.getId(), item.getContent(), item.getFinishStatus());
        } else
            return null;
    }

    @Override
    public List<ItemDTO> findAllByTaskId(Long taskId) {
        return itemRepository.findByTaskId(taskId);
    }

}