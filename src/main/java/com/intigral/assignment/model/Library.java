package com.intigral.assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Library {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Object start_time;
    Object end_time;
}
