package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateInterviewProcessRepository extends CrudRepository<CandidateInterviewProcess, Long> {
    List<CandidateInterviewProcess> findByEmailId(String emailId);
}
