package com.dtdl.hunter.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public class VacancyModel {

    private String jobId;

    private Integer openPositions;

    private String role;

    private String experience;

    private String location;

    private String description;

    private String created_by;

}
