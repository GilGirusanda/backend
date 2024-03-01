package com.rowskx.todo.DTOs.Task;

import java.time.LocalDateTime;

public record TaskAddRecord(Long list_id, String header, String content, String date_time, Boolean reminder) {

}
