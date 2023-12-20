package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "event_id_seq")
    @SequenceGenerator(name = "event_id_seq", sequenceName = "event_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String header;

    @Column
    private String content;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

}
