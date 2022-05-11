package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import com.dtdl.hunter.model.Interview;
import com.dtdl.hunter.repository.CandidateInterviewProcessRepository;
import com.dtdl.hunter.service.CandidateInterviewProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateInterviewProcessServiceImpl implements CandidateInterviewProcessService {

    @Autowired
    CandidateInterviewProcessRepository candidateInterviewProcessRepository;

    @Override
    public List<Interview> getAllUpcomingInterviewForGivenUser(String userId) {
        List<CandidateInterviewProcess> interviews = candidateInterviewProcessRepository.findByUserSlotEmployeeId(userId);
        List<Interview> interviewsList = interviews.stream().map(interview -> new Interview(interview.getId())).collect(Collectors.toList());
        return interviewsList;
    }
}
