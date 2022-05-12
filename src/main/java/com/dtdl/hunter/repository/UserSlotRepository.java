package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.CandidateInterviewProcess;
import com.dtdl.hunter.entity.UserSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSlotRepository extends CrudRepository<UserSlot, Long> {

    @Query(value="SELECT * FROM user_slot us WHERE us.speciality = :speciality and us.interview_date >= now() and us.id not in (select user_slot_id from candidate_interview_process where user_slot_id is not null) ",nativeQuery = true)
   List<UserSlot> findAllBySpecialityAndInterviewDateAfterNow(String speciality);

    @Query(value="SELECT * FROM user_slot us WHERE us.employee_id = :employeeId and us.interview_date >= now() and us.id not in (select user_slot_id from candidate_interview_process where user_slot_id is not null) ",nativeQuery = true)
    List<UserSlot> getMyAvailableSlots(String employeeId);
}
