package com.rowskx.todo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {

    // @Id
    // @Column(name = "id", columnDefinition = "bigserial")
    // @GeneratedValue(strategy = GenerationType.IDENTITY, generator =
    // "team_id_seq")
    // @SequenceGenerator(name = "team_id_seq", sequenceName = "team_id_seq",
    // initialValue = 1, allocationSize = 1)
    // private Long id;

    // @Column(nullable = false, length = 100)
    // private String name;

    // @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<TeamList> teamLists = new ArrayList<>();

    // @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<OwnerTeam> ownerTeams = new ArrayList<>();

    // @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<TeamMember> teamMembers = new ArrayList<>();

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;
}
