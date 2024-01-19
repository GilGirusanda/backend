package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "list_task", uniqueConstraints = {
        @UniqueConstraint(name = "listtask_task_id_unique", columnNames = "task_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListTask {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name =
    // "fk_list_task_list"), nullable = false)
    private ListEntity list;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name =
    // "fk_list_task_task"), nullable = false, unique = true)
    private Task task;

}
