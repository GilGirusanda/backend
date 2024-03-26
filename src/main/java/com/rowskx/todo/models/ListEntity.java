package com.rowskx.todo.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lists")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListEntity {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String header;

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Event> events = new HashSet<>();

    public ListEntity(String header) {
        this.header = header;
    }
}
