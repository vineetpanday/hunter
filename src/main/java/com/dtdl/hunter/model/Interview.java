package com.dtdl.hunter.model;

import lombok.Data;

@Data
public class Interview {

    private long id;

    private String roundType;

    private UserSlotModel userSlot;

    private String feedback;

    private String result;

    private CandidateModel candidate;

    private String interviewer;

}
