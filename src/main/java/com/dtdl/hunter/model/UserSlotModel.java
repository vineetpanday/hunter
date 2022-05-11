package com.dtdl.hunter.model;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
public class UserSlotModel {

    private String employeeId;

    private Date interviewDate;

    private String designation;

    private String speciality;

}
