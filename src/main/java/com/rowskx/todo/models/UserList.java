package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_list_id_seq")
    @SequenceGenerator(name = "user_list_id_seq", sequenceName = "user_list_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "list_id", nullable = false, unique = true)
    // @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name =
    // "fk_user_list_list"), nullable = false, unique = true)
    private List list;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    // @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name =
    // "fk_user_list_user"), nullable = false)
    private User user;
}
