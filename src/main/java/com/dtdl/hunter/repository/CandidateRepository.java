package com.dtdl.hunter.repository;

import com.dtdl.hunter.constant.StringConstant;
import com.dtdl.hunter.entity.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    @Override
    <S extends Candidate> S save(S entity);

    List<Candidate> findAllByStatus(String status);

    List<Candidate> findAllByReferredBy(String referredBy);


    List<Candidate> findAllByStatusAndAndPosition(String status, String position);

    long countByHrSpoc(String name);

    long countByHrSpocAndStatus(String name, String status);

    long countByHrSpocAndStatusIn(String name, List<String> status);

}
