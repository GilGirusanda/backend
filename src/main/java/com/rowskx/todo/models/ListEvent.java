package com.rowskx.todo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "list_event")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ListEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "list_event_id_seq")
    @SequenceGenerator(name = "list_event_id_seq", sequenceName = "list_event_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "list_id", nullable = false)
//    @JoinColumn(name = "list_id", foreignKey = @ForeignKey(name = "fk_list_event_list"), nullable = false)
    private List list;

    @OneToOne
    @JoinColumn(name = "event_id", nullable = false, unique = true)
//    @JoinColumn(name = "event_id", foreignKey = @ForeignKey(name = "fk_list_event_event"), nullable = false, unique = true)
    private Event event;

}
