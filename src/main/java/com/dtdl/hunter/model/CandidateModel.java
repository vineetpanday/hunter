package com.dtdl.hunter.model;

import lombok.Data;

import java.util.Date;

@Data
public class CandidateModel {
    private long id;
    private String name;
    private Date referralDate;
    private String referredBy;
    private String position;
    private String status;
    private String emailId;
    private Long resumeId;
    private String linkedInId;
    private String result;
    private String hrSpoc;
}
