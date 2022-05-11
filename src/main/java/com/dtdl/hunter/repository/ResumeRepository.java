package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.Resume;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ResumeRepository extends CrudRepository<Resume, Long> {

    @Override
    Optional<Resume> findById(Long aLong);
}
