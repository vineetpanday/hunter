package com.dtdl.hunter.controller;


import com.dtdl.hunter.model.Interview;
import com.dtdl.hunter.service.CandidateInterviewProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateInterviewProcessController {

    @Autowired
    CandidateInterviewProcessService candidateInterviewProcessService;

    @RequestMapping(value="v1/getAllUpcomingInterviewForGivenUser",method= RequestMethod.GET)
    @ResponseBody
    public List<Interview> getAllUpcomingInterviewForGivenUser(@RequestParam(value="emailId") String emailId){
     //where on result lagana rh gya h
      return  candidateInterviewProcessService.getAllUpcomingInterviewForGivenUser(emailId);
    }

    @RequestMapping(value="v1/scheduleInterview",method= RequestMethod.POST)
    @ResponseBody
    public void scheduleInterview(@RequestBody Interview interview){
          candidateInterviewProcessService.scheduleInterview(interview);
    }

    @RequestMapping(value="v1/updateInterviewResult",method= RequestMethod.POST)
    @ResponseBody
    public void updateInterviewResult(@RequestBody Interview interview){
        candidateInterviewProcessService.updateInterviewResult(interview);
    }

}
