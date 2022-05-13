package com.dtdl.hunter.model;

import lombok.Data;

@Data
public class MyPanelDto {
    private String employeeId;
    private String result;
    private String roundType;
    private Long candidateId;
    private String name;
    private String referredBy;
}
