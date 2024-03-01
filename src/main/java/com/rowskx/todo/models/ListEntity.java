package com.rowskx.todo.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lists")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListEntity {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String header;

    @ManyToMany
    @JoinTable(name = "list_task", joinColumns = @JoinColumn(name = "list_id"), inverseJoinColumns = @JoinColumn(name = "task_id", unique = true))
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "list_event", joinColumns = @JoinColumn(name = "list_id"), inverseJoinColumns = @JoinColumn(name = "event_id", unique = true))
    private List<Event> events = new ArrayList<>();

    public ListEntity(String header) {
        this.header = header;
    }
}
