package com.dtdl.hunter.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name="candidate")
@Data
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

    @Column(name="email_Id")
    private String emailId;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="resume_id")
    private Resume resume;

    @Column(name="linkedIn_id")
    private String linkedInId;

    @Column(name="result")
    private String result;

    @Column(name="hr_spoc")
    private String hrSpoc;

    @OneToMany(mappedBy = "candidate")
    private Set<CandidateInterviewProcess> candidateInterviewProcesss;

}
