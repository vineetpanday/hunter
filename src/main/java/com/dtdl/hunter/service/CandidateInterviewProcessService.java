package com.dtdl.hunter.service;

import com.dtdl.hunter.model.Interview;


import java.util.List;

public interface CandidateInterviewProcessService {


    List<Interview> getAllUpcomingInterviewForGivenUser(String userId);
}
