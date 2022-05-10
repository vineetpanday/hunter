package com.dtdl.hunter.controller;


import com.dtdl.hunter.model.Interview;
import com.dtdl.hunter.service.CandidateInterviewProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidateInterviewProcessController {

    @Autowired
    CandidateInterviewProcessService candidateInterviewProcessService;

    @RequestMapping(value="v1/getAllUpcomingInterviewForGivenUser",method= RequestMethod.GET)
    public List<Interview> getAllUpcomingInterviewForGivenUser(@RequestParam(value="emailId") String emailId){
      return  candidateInterviewProcessService.getAllUpcomingInterviewForGivenUser(emailId);
    }

}
