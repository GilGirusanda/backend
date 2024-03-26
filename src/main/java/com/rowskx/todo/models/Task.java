package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String header;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean reminder = false;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false, columnDefinition = "BIGINT")
    private ListEntity list;

//    @OneToMany /* (fetch = FetchType.EAGER) */
//    @JoinTable(
//            name = "task_comment",
//            joinColumns = @JoinColumn(name = "task_id", nullable = false, columnDefinition = "BIGINT"),
//            inverseJoinColumns = @JoinColumn(name = "comment_id", unique = true, nullable = false, columnDefinition = "BIGINT"))
//    private List<Comment> comments = new ArrayList<>();

    // @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<TaskComment> taskComments = new ArrayList<>();
}
