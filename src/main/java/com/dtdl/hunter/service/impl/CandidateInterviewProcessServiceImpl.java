package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.entity.Candidate;
import com.dtdl.hunter.entity.CandidateInterviewProcess;
import com.dtdl.hunter.entity.UserSlot;
import com.dtdl.hunter.model.CandidateModel;
import com.dtdl.hunter.model.EmployeeModel;
import com.dtdl.hunter.model.Interview;
import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.repository.CandidateInterviewProcessRepository;
import com.dtdl.hunter.repository.CandidateRepository;
import com.dtdl.hunter.repository.EmployeeRepository;
import com.dtdl.hunter.repository.UserSlotRepository;
import com.dtdl.hunter.service.CandidateInterviewProcessService;
import com.dtdl.hunter.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateInterviewProcessServiceImpl implements CandidateInterviewProcessService {

    @Autowired
    CandidateInterviewProcessRepository candidateInterviewProcessRepository;
    @Autowired
    UserSlotRepository userSlotRepository;
    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    SessionService sessionService;

    @Override
    public List<Interview> getAllUpcomingInterviewForGivenUser(String userId) {
        List<CandidateInterviewProcess> interviews = candidateInterviewProcessRepository.findByUserSlotEmployeeIdAndResultIsNull(userId);
        List<Interview> interviewsList = interviews.stream().map(interview -> getInterviewBean(interview)).collect(Collectors.toList());
        return interviewsList;
    }

    private Interview getInterviewBean(CandidateInterviewProcess interviewProcess) {
        CandidateModel candidate=new CandidateModel();
        candidate.setId(interviewProcess.getCandidate().getId());
        candidate.setHrSpoc(interviewProcess.getCandidate().getHrSpoc());
        candidate.setName(interviewProcess.getCandidate().getName());
        candidate.setResumeId(interviewProcess.getCandidate().getResume().getId());
        UserSlotModel slot=new UserSlotModel();
        slot.setId(interviewProcess.getUserSlot().getId());
        slot.setDesignation(interviewProcess.getUserSlot().getDesignation());
        slot.setSpeciality(interviewProcess.getUserSlot().getSpeciality());
        slot.setInterviewDate(interviewProcess.getUserSlot().getInterviewDate());
        Interview interview=new Interview();
        interview.setCandidate(candidate);
        interview.setId(interviewProcess.getId());
        interview.setUserSlot(slot);
        interview.setRoundType(interviewProcess.getRoundType());
        return interview;
    }

    @Override
    public void scheduleInterview(Interview interview) {
        Optional<UserSlot> slot = userSlotRepository.findById(interview.getUserSlot().getId());
        Optional<Candidate> candidate = candidateRepository.findById(interview.getCandidate().getId());
        candidate.get().setStatus(StringConstant.Status.InProcess.value);
        EmployeeModel employee = sessionService.getEmployee(interview.getCandidate().getHrSpoc());
        candidate.get().setHrSpoc(employee.getName());
        //check agr final save se update ho jaye to remove it
        Candidate updatedCandidate = candidateRepository.save(candidate.get());
        CandidateInterviewProcess candidateInterviewProcess=new CandidateInterviewProcess();
        candidateInterviewProcess.setUserSlot(slot.get());
        candidateInterviewProcess.setRoundType(interview.getRoundType());
        candidateInterviewProcess.setCandidate(updatedCandidate);
        candidateInterviewProcessRepository.save(candidateInterviewProcess);
    }

    @Override
    public void updateInterviewResult(Interview interview) {
        CandidateInterviewProcess interviewProcess = candidateInterviewProcessRepository.findById(interview.getId()).get();
        interviewProcess.setResult(interview.getResult());
        interviewProcess.setFeedback(interview.getFeedback());
        if(StringConstant.Status.Rejected.value.equals(interview.getResult())){
            interviewProcess.getCandidate().setStatus(StringConstant.Status.Rejected.value);
            candidateRepository.save(interviewProcess.getCandidate());
        }
        candidateInterviewProcessRepository.save(interviewProcess);
    }
}
