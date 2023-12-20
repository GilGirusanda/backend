package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lists")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "list_id_seq")
    @SequenceGenerator(name = "list_id_seq", sequenceName = "list_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 255)
    private String header;

}
