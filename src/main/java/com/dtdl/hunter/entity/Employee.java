package com.dtdl.hunter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="employee")
public class Employee {

    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="email_id")
    private String emailId;

    @Column(name="name")
    private String name;

    @Column(name="designation")
    private String designation;

    @Column(name="speciality")
    private String speciality;


}
