package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_list", uniqueConstraints = {
        @UniqueConstraint(name = "userlist_list_id_unique", columnNames = "list_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserList {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // @JoinColumn(name = "list_id", nullable = false, unique = true) // ,
    // insertable = false, updatable = false
    @JoinColumn(name = "list_id", nullable = false, columnDefinition = "BIGINT")
    private ListEntity list;

    @ManyToOne
    // @JoinColumn(name = "user_id", nullable = false) // , insertable = false,
    // updatable = false
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private User user;
}
