package com.dtdl.hunter.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity(name ="employee")
@Data
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

    @Column(name="password")
    private String password;


}
