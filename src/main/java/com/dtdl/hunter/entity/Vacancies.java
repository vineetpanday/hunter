package com.dtdl.hunter.entity;

import javax.persistence.*;

@Entity(name = "vacancies")
public class Vacancies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="open_position")
    private Integer openPosition;

    @Column(name="role")
    private String role;

    @Column(name="experience")
    private String experience;

    @Column(name="location")
    private String location;

    @Column(name="description")
    private String description;

    @Column(name="created_by")
    private String created_by;


}
