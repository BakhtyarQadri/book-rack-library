package com.intigral.assignment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Rack {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer rowNumber;
    Integer columnNumber;

    @Column(name = "library_fid")
    Integer libraryId;
    // Library library;
}