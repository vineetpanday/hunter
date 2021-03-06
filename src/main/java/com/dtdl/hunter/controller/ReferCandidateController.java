package com.dtdl.hunter.controller;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.model.Candidate;
import com.dtdl.hunter.model.MyPanelDto;
import com.dtdl.hunter.service.ReferACandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ReferCandidateController {

@Autowired
   private ReferACandidateService service;

    @RequestMapping(value="v1/referCandidate",method= RequestMethod.POST)
    public ResponseEntity<String> referCandidate(@RequestParam("resume") MultipartFile resume,
                                         @RequestParam("name") String name,
                                         @RequestParam("email") String email,
                                         @RequestParam("position") String position,
                                         @RequestParam("phone") String phone,
                                         @RequestParam("referredBy") String referredBy){

    service.referCandidate(resume, name, email, position, phone, referredBy);

    return ResponseEntity.ok("Success");
    }

    @GetMapping(value="v1/markCandidateNotInterested")
    public void markCandidateNotInterested(@RequestParam  Long id){
     service.markCandidateNotInterested(id);

    }


    @GetMapping(value="v1/getAllCandidatesToBeReviewed")
    public List<Candidate> getAllCandidatesToBeReviewed(@RequestParam String filter){
      return service.getAllCandidatesToBeReviewed(filter);
 }

    @GetMapping(value="v1/getUserReferrals")
    public ResponseEntity<List<Candidate>> getUserReferrals(@RequestParam String userId){

        List<Candidate> candidates =  service.getUserReferrals(userId);
        return ResponseEntity.ok(candidates);
 }

    @GetMapping(value="v1/acceptOrRejectByHr")
    public void markAcceptOrRejectByHr(@RequestParam Long candidateId  , @RequestParam String result){
        service.markAcceptOrRejectByHr(candidateId, result);
    }

    @GetMapping(value="v1/getCandidatesMappedToHr")
    public List<MyPanelDto> getCandidatesMappedToHr(@RequestParam String userId){
       return service.getCandidatesMappedToHr(userId, StringConstant.Status.InProcess.value);

    }




}
