package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "comments_id_seq")
    @SequenceGenerator(name = "comments_id_seq", sequenceName = "comments_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String content;

}
