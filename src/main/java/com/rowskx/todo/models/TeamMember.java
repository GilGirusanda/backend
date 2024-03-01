package com.rowskx.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team_member")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeamMember {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "team_id")
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name =
    // "fk_team_member_team"), nullable = false)
    private Team team;

    // @Column(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    // @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name =
    // "fk_team_member_user"), nullable = false)
    private User user;

    @Column(name = "rate_value", nullable = false, columnDefinition = "smallint default 0")
    @Min(value = 0, message = "Rate Value must be greater than or equal to 0")
    @Max(value = 1000, message = "Rate Value must be less than or equal to 1000")
    private Short rateValue;

}
