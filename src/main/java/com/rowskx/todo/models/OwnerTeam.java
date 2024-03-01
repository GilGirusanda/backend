package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner_team", uniqueConstraints = {
        @UniqueConstraint(name = "ownerteam_team_id_unique", columnNames = "team_id")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerTeam {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name =
    // "fk_owner_team_team"), nullable = false, unique = true)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name =
    // "fk_owner_team_user"), nullable = false)
    private User user;

}
