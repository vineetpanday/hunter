package com.dtdl.hunter.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Candidate {

    private long id;

    private String name;

    private Date referralDate;

    private String referredBy;

    private String position;

    private String status;

    private String emailId;

    private Long resumeId;

    private String phone;

    private String result;

    private String hrSpoc;

    private List<Interview> interviewProcessList;

}
