package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_list")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "team_list_id_seq")
    @SequenceGenerator(name = "team_list_id_seq", sequenceName = "team_list_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "list_id", nullable = false, unique = true)
//    @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name = "fk_team_list_list"), nullable = false, unique = true)
    private List list;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
//    @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "fk_team_list_team"), nullable = false)
    private Team team;

}
