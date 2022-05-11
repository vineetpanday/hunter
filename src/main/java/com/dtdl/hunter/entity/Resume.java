package com.dtdl.hunter.entity;

import lombok.Data;

import javax.persistence.*;
@Entity(name="resume")
@Data
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="email_Id")
    String emailId;

    @Column(name="resume")
    byte[] resume;

    @Column(name="file_Name")
    String fileName;

    @OneToOne(mappedBy = "resume", cascade =  CascadeType.ALL)
    Candidate candidate;

}
