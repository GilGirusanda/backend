package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner_team")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "owner_team_id_seq")
    @SequenceGenerator(name = "owner_team_id_seq", sequenceName = "owner_team_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "team_id", nullable = false, unique = true)
//    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "fk_owner_team_team"), nullable = false, unique = true)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
//    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_owner_team_user"), nullable = false)
    private User user;

}
