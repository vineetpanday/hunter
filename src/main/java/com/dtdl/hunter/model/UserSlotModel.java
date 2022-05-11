package com.dtdl.hunter.model;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSlotModel {
    private long id;

    private String employeeId;

    private Date interviewDate;

    private String designation;

    private String speciality;

}
