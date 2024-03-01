package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_comment", uniqueConstraints = {
        @UniqueConstraint(name = "taskcomment_comment_id_unique", columnNames = "comment_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskComment {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false, columnDefinition = "BIGINT")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name =
    // "fk_task_comment_task"), nullable = false)
    private Task task;

}
