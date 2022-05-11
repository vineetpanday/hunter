package com.dtdl.hunter.service;

import com.dtdl.hunter.model.ReferCandidateInput;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ReferACandidateService {

    public void referCandidate( MultipartFile resume, String name, String email, String position, String linkedInId, String referredBy);
}
