package com.dtdl.hunter.service;

import com.dtdl.hunter.entity.Resume;

import java.util.Optional;

public interface ResumeService {

    Optional<Resume> getResume(Long id);
}
