package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.entity.*;
import com.dtdl.hunter.model.EmployeeModel;
import com.dtdl.hunter.model.Mail;
import com.dtdl.hunter.model.MyPanelDto;
import com.dtdl.hunter.repository.CandidateRepository;
import com.dtdl.hunter.repository.ResumeRepository;
import com.dtdl.hunter.repository.VacancyRepository;
import com.dtdl.hunter.service.MailService;
import com.dtdl.hunter.service.ReferACandidateService;
import com.dtdl.hunter.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.Date;

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


    }

    private String fetchNameFromEmailId(String referredBy) {
       EmployeeModel employee = sessionService.getEmployee(referredBy);
       if(employee!=null) {
           return employee.getName();
       }
       return null;
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

        String referredByName = fetchNameFromEmailId(referredBy);

        Thread t = new Thread(() -> {
            sendMail(email,name, referredByName, candidate.getPosition());
        });
       t.start();
    }

    private void sendMail(String receiverMail, String receiverName, String referredBy, String position)
    {
        Mail mail = new Mail();
        mail.setMailFrom("dtdlgurgaon@gmail.com");
        mail.setMailTo(receiverMail);

        String subject = "Job Referral - ".concat(position).concat("  at Deutsche Telekom - DL");
        mail.setMailSubject(subject);

        String return_value=String.format("Dear %s,",receiverName)+"\n\n"+
                String.format("Congratulations! You've been referred to Deutsche Telekom Digital Labs for the profile of %s by %s today. ",position,referredBy)+
                "Our team is reviewing your profile and will get in touch with you in case we find you a good match.\n\n\n" +
                "Thank you for your interest in Deutsche Telekom.\n\n\n"+
                "Regards,\n"+
                "Talent Acquisition Team,DTDL\n"+
                "Gurugram";
        mail.setMailContent(return_value);
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
    public List<MyPanelDto> getCandidatesMappedToHr(String userId, String result) {

        EmployeeModel emp = sessionService.getEmployee(userId);

        List<MyPanelDto> panelList=new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");
            Connection c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/hunter_db",
                            "postgres", "");
            PreparedStatement stmt = c.prepareStatement("select employee_id,ss.result,round_type,candidate_id,c.name,c.referred_by from (select us.employee_id, cip.result, cip.round_type, cip.candidate_id from user_slot us join candidate_interview_process cip on cip.user_slot_id=us.id order by us.interview_date desc limit 1) as ss join candidate c on c.id=ss.candidate_id where c.hr_spoc=? and c.status='In Process'");
            stmt.setString(1,emp.getName());
            ResultSet rsltSet = stmt.executeQuery();
            while(rsltSet.next()){
               MyPanelDto panel=new MyPanelDto();
                panel.setEmployeeId(rsltSet.getString(1));
                panel.setResult(rsltSet.getString(2));
                panel.setRoundType(rsltSet.getString(3));
                panel.setCandidateId(rsltSet.getLong(4));
                panel.setName(rsltSet.getString(5));
                panel.setReferredBy(rsltSet.getString(6));
                panelList.add(panel);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



        return panelList;
    }

}
