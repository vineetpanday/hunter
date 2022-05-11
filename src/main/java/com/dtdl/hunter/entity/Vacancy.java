package com.dtdl.hunter.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "vacancy")
@Data
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="job_Id")
    private String jobId;

    @Column(name="open_positions")
    private Integer openPositions;

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
