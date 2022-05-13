package com.dtdl.hunter.service;

import com.dtdl.hunter.model.Candidate;
import com.dtdl.hunter.model.MyPanelDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReferACandidateService {

     void referCandidate( MultipartFile resume, String name, String email, String position, String phone, String referredBy);

     void markCandidateNotInterested(Long id);

     List<Candidate> getAllCandidatesToBeReviewed(String filter);

     List<com.dtdl.hunter.model.Candidate> getUserReferrals(String userId);

     void markAcceptOrRejectByHr(Long id, String result);

     List<MyPanelDto> getCandidatesMappedToHr(String userId, String result);

}
