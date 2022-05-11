package com.dtdl.hunter.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name="user_slot")
@Data
public class UserSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="employee_id")
    private String employeeId;

    @Column(name="interview_date")
    private Date interviewDate;

    @Column(name="designation")
    private String designation;

    @Column(name="speciality")
    private String speciality;

    @OneToOne(mappedBy = "userSlot")
    CandidateInterviewProcess candidateInterviewProcess;

}
