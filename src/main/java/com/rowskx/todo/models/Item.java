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
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String content;

    @Column(name = "finish_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean finishStatus;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
//    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_item_task"), nullable = false)
    private Task task;
}
