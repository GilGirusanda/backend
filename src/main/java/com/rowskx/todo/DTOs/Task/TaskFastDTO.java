package com.rowskx.todo.DTOs.Task;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskFastDTO {

    private Long id;
    private String header;
    private String content;
    private LocalDateTime date_time;
    private Boolean reminder;

}
