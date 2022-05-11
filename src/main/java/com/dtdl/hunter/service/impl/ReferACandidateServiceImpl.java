package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.Candidate;
import com.dtdl.hunter.entity.Resume;
import com.dtdl.hunter.model.Mail;
import com.dtdl.hunter.repository.CandidateRepository;
import com.dtdl.hunter.repository.ResumeRepository;
import com.dtdl.hunter.service.MailService;
import com.dtdl.hunter.service.ReferACandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class ReferACandidateServiceImpl implements ReferACandidateService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private MailService mailService;

    @Override
    @Transactional
    public void referCandidate(MultipartFile resume, String name, String email, String position, String linkedInId, String referredBy) {

        saveDataInDb( resume,  name,  email,  position,  linkedInId,  referredBy);
        sendMail(email);


    }

    private void saveDataInDb(MultipartFile resume, String name, String email, String position, String linkedInId, String referredBy){
        Candidate candidate = new Candidate();
        candidate.setEmailId(email);
        candidate.setName(name);
        candidate.setPosition(position);
        candidate.setLinkedInId(linkedInId);

        Date date = new Date();
        candidate.setReferralDate(date);
        candidate.setReferredBy(referredBy);
        candidate.setStatus("To be reviewed");

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
        mail.setMailContent("Hi, \n\n\n You have been referred at DT. We will contact you after analysing your resume!!\n\nThanks\nDTDL");

        mailService.sendEmail(mail);
    }
}
