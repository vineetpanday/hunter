package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.Candidate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

    @Override
    <S extends Candidate> S save(S entity);
}
