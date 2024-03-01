package com.rowskx.todo.repositories;

import com.rowskx.todo.DTOs.Task.TaskFastDTO;
import com.rowskx.todo.models.Task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

        @Query(value = "SELECT * FROM insert_task_function(CAST(:listId AS BIGINT), CAST(:header AS VARCHAR), CAST(:dateTime AS TIMESTAMP), CAST(:reminder AS BOOLEAN), CAST(:content AS VARCHAR));", nativeQuery = true)
        void addTask(@Param("listId") Long listId, @Param("header") String header,
                        @Param("content") String content,
                        @Param("dateTime") LocalDateTime dateTime, @Param("reminder") Boolean reminder);

        @Query(value = "SELECT new com.rowskx.todo.DTOs.Task.TaskFastDTO(t.id, t.header, t.content, t.dateTime, t.reminder)  FROM Task t INNER JOIN ListTask lt ON t.id = lt.task.id WHERE lt.list.id = :listId")
        List<TaskFastDTO> findAllByListId(@Param("listId") Long listId);

        @Query(value = "SELECT * FROM delete_user_task(CAST(:taskId AS BIGINT));", nativeQuery = true)
        void delete(@Param("taskId") Long taskId);

        @Query(value = "SELECT * FROM update_task(CAST(:taskId AS BIGINT), CAST(:newHeader AS VARCHAR), CAST(:newContent AS VARCHAR), CAST(:newDateTime AS TIMESTAMP), CAST(:newReminder AS BOOLEAN));", nativeQuery = true)
        void update(@Param("taskId") Long taskId, @Param("newHeader") String newHeader,
                        @Param("newContent") String newContent, @Param("newDateTime") LocalDateTime newDateTime,
                        @Param("newReminder") Boolean newReminder);

}
