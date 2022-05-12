package com.dtdl.hunter.constant;

public interface StringConstant {

    enum Status {

        InReview("To be reviewed"),InProcess("In Process"),OnHold("On Hold"),Rejected("Rejected"),Selected("Selected"),CandidateNotInterested("Candidate not interested");
        public final String value;
       private Status(String value){
            this.value=value;
        }
    }


}
