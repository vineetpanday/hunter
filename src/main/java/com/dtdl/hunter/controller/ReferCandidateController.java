package com.dtdl.hunter.controller;

import com.dtdl.hunter.model.Candidate;
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
                                         @RequestParam("linkedInId") String linkedInId,
                                         @RequestParam("referredBy") String referredBy){

    service.referCandidate(resume, name, email, position, linkedInId, referredBy);

    return ResponseEntity.ok("Success");
    }

    @GetMapping(value="v1/markCandidateNotInterested")
    public void markCandidateNotInterested(@RequestParam  Long id){
     service.markCandidateNotInterested(id);

    }


    @GetMapping(value="v1/getAllCandidatesToBeReviewed")
    public List<Candidate> markCandidateNotInterested(){
      return service.getAllCandidatesToBeReviewed();

 }
}
