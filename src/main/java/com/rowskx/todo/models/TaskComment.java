package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "task_comment_id_seq")
    @SequenceGenerator(name = "task_comment_id_seq", sequenceName = "task_comment_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = false, unique = true)
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
//    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_task_comment_task"), nullable = false)
    private Task task;

}
