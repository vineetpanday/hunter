package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.Resume;
import com.dtdl.hunter.repository.ResumeRepository;
import com.dtdl.hunter.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.util.Optional;

@Repository
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;


    @Override
    public Optional<Resume> getResume(Long id) {
     return resumeRepository.findById(id);


    }
}
