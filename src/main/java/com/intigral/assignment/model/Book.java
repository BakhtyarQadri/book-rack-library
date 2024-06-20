package com.intigral.assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String description;

    @ManyToOne
    @JoinColumn(name = "rack_id_fk", nullable = false)
    Rack rack;

}
