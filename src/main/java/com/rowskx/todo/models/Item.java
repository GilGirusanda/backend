package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "finish_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean finishStatus;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name =
    // "fk_item_task"), nullable = false)
    private Task task;
}
