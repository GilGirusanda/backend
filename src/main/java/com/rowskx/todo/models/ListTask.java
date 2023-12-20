package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "list_task")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "list_task_id_seq")
    @SequenceGenerator(name = "list_task_id_seq", sequenceName = "list_task_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
//    @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name = "fk_list_task_list"), nullable = false)
    private List list;

    @OneToOne
    @JoinColumn(name = "task_id", nullable = false, unique = true)
//    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "fk_list_task_task"), nullable = false, unique = true)
    private Task task;

}
