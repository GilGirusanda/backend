package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_list", uniqueConstraints = {
        @UniqueConstraint(name = "teamlist_list_id_unique", columnNames = "list_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamList {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name =
    // "fk_team_list_list"), nullable = false, unique = true)
    private ListEntity list;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name =
    // "fk_team_list_team"), nullable = false)
    private Team team;

}
