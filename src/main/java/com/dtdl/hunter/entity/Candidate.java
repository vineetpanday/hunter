package com.dtdl.hunter.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name="candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="referral_date")
    private Date referralDate;

    @Column(name="referred_by")
    private String referredBy;

    @Column(name="position")
    private String position;

    @Column(name="status")
    private String status;

    @Column(name="email_id")
    private String emailId;

    @Column(name="linkedIn_id")
    private String linkedInId;

    @Column(name="result")
    private String result;

    @Column(name="hr_spoc")
    private String hrSpoc;




}
