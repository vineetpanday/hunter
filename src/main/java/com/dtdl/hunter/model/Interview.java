package com.dtdl.hunter.model;

import lombok.Data;

@Data
public class Interview {

    private long id;


    private String emailId;


    private String roundType;

    public Interview(long id) {
        this.id=id;
    }

    private Long slotId;


    private String feedback;


    private String result;
}
