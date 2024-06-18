package com.intigral.assignment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
