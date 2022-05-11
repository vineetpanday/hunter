package com.dtdl.hunter.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "candidate_interview_process")
@Data
public class CandidateInterviewProcess {
    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "round_type")
    private String roundType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_slot_id", referencedColumnName = "id")
    private UserSlot userSlot;

    @Column(name="feedback")
    private String feedback;

    @Column(name="result")
    private String result;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    Candidate candidate;
}
