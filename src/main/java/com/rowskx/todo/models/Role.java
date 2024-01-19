package com.rowskx.todo.models;

import com.rowskx.todo.models.enums.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(name = "roles_name_unique", columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    @Column(name = "id", updatable = false, columnDefinition = "BIGSERIAL")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }

    public Role(String name) {
        this.name = ERole.valueOf(name);
    }
}
