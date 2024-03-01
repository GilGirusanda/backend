package com.rowskx.todo.DTOs.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String content;
    private Boolean finish_status;
    // private Long task_id;

    // public ItemDTO(Long id, String content, Boolean finish_status) {
    // this.id = id;
    // this.content = content;
    // this.finish_status = finish_status;
    // this.task_id = null;
    // }
}
