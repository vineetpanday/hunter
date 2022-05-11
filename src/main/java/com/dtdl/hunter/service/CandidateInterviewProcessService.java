package com.dtdl.hunter.service;

import com.dtdl.hunter.model.Interview;
import org.springframework.stereotype.Service;


import java.util.List;

public interface CandidateInterviewProcessService {


    List<Interview> getAllUpcomingInterviewForGivenUser(String userId);
}
