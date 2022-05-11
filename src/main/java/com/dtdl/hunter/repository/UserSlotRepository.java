package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import com.dtdl.hunter.entity.UserSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSlotRepository extends CrudRepository<UserSlot, Long> {
}
