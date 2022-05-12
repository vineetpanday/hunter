package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.entity.Candidate;
import com.dtdl.hunter.entity.Resume;
import com.dtdl.hunter.entity.Vacancy;
import com.dtdl.hunter.model.EmployeeModel;
import com.dtdl.hunter.model.Mail;
import com.dtdl.hunter.repository.CandidateRepository;
import com.dtdl.hunter.repository.ResumeRepository;
import com.dtdl.hunter.repository.VacancyRepository;
import com.dtdl.hunter.service.MailService;
import com.dtdl.hunter.service.ReferACandidateService;
import com.dtdl.hunter.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReferACandidateServiceImpl implements ReferACandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public void referCandidate(MultipartFile resume, String name, String email, String position, String phone, String referredBy) {

        saveDataInDb( resume,  name,  email,  position,  phone,  referredBy);
        sendMail(email);


    }

    private void saveDataInDb(MultipartFile resume, String name, String email, String position, String phone, String referredBy){
        Candidate candidate = new Candidate();
        candidate.setEmailId(email);
        candidate.setName(name);

        Optional<Vacancy> vacancy = vacancyRepository.findById(Long.valueOf(position));
        if(vacancy.isPresent()){
            candidate.setPosition(vacancy.get().getRole());
        }

        candidate.setPhone(phone);

        Date date = new Date();
        candidate.setReferralDate(date);
        candidate.setReferredBy(referredBy);
        candidate.setStatus(StringConstant.Status.InReview.value);

        Resume r = new Resume();

        r.setFileName(resume.getName());
        try {
            r.setResume(resume.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        candidate.setResume(r);
        r.setCandidate(candidate);
        resumeRepository.save(r);
    }

    private void sendMail(String receiverId)
    {
        Mail mail = new Mail();
        mail.setMailFrom("dtdlgurgaon@gmail.com");
        mail.setMailTo(receiverId);
        mail.setMailSubject("You have been referred at DT");
        mail.setMailContent("Hi, \n\nYou have been referred at DT. We will contact you after analysing your resume!!\n\nThanks\nDTDL");
        mailService.sendEmail(mail);
    }


    public void markCandidateNotInterested(Long id){
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()){
            candidate.get().setStatus(StringConstant.Status.CandidateNotInterested.value);
            candidateRepository.save(candidate.get());
        }
    }

    public List<com.dtdl.hunter.model.Candidate> getAllCandidatesToBeReviewed(String filter){
        List<Candidate> candidates = null;
        if(filter!=null && filter!=""){
            candidates = candidateRepository.findAllByStatusAndAndPosition(StringConstant.Status.InReview.value, filter);
        }else {
             candidates = candidateRepository.findAllByStatus(StringConstant.Status.InReview.value);
        }
      List<com.dtdl.hunter.model.Candidate> candidatesDtoList = new ArrayList<>();

      candidates.forEach( c->{
          com.dtdl.hunter.model.Candidate candidate = new com.dtdl.hunter.model.Candidate();

          candidate.setId(c.getId());;
          candidate.setName(c.getName());
          candidate.setReferralDate(c.getReferralDate());
          candidate.setReferredBy(c.getReferredBy());
          candidate.setPosition(c.getPosition());
          candidate.setStatus(c.getStatus());
          candidate.setEmailId(c.getEmailId());
          candidate.setResumeId(c.getResume().getId());
          candidate.setPhone(c.getPhone());
          candidate.setResult(c.getResult());
          candidate.setHrSpoc(c.getHrSpoc());
          candidatesDtoList.add(candidate);
      });
      return candidatesDtoList;
    }

    @Override
    public List<com.dtdl.hunter.model.Candidate> getUserReferrals(String userId) {
        List<Candidate> candidates = candidateRepository.findAllByReferredBy(userId);

        List<com.dtdl.hunter.model.Candidate> candidatesDtoList = new ArrayList<>();

        candidates.forEach( c->{
            com.dtdl.hunter.model.Candidate candidate = new com.dtdl.hunter.model.Candidate();

            candidate.setId(c.getId());;
            candidate.setName(c.getName());
            candidate.setReferralDate(c.getReferralDate());
            candidate.setReferredBy(c.getReferredBy());
            candidate.setPosition(c.getPosition());
            candidate.setStatus(c.getStatus());
            candidate.setEmailId(c.getEmailId());
            candidate.setResumeId(c.getResume().getId());
            candidate.setPhone(c.getPhone());
            candidate.setResult(c.getResult());
            candidate.setHrSpoc(c.getHrSpoc());
            candidatesDtoList.add(candidate);
        });

        return candidatesDtoList;
    }

    @Override
    public void markAcceptOrRejectByHr(Long id, String result) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if(candidate.isPresent()){
            candidate.get().setStatus(result);
            candidateRepository.save(candidate.get());
        }
    }

    @Override
    public List<com.dtdl.hunter.model.Candidate> getCandidatesMappedToHr(String userId, String result) {
        List<Candidate> candidates =  candidateRepository.findAllByHrSpocAndStatus(userId, result);


        List<com.dtdl.hunter.model.Candidate> candidatesDtoList = new ArrayList<>();

        candidates.forEach( c->{
            com.dtdl.hunter.model.Candidate candidate = new com.dtdl.hunter.model.Candidate();

            candidate.setId(c.getId());;
            candidate.setName(c.getName());
            candidate.setReferralDate(c.getReferralDate());
            candidate.setReferredBy(c.getReferredBy());
            candidate.setPosition(c.getPosition());
            candidate.setStatus(c.getStatus());
            candidate.setEmailId(c.getEmailId());
            candidate.setResumeId(c.getResume().getId());
            candidate.setPhone(c.getPhone());
            candidate.setResult(c.getResult());
            candidate.setHrSpoc(c.getHrSpoc());

            candidatesDtoList.add(candidate);
        });
        return candidatesDtoList;
    }

}
