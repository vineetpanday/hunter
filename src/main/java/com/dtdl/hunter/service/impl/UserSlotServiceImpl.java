package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.UserSlot;
import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.repository.UserSlotRepository;
import com.dtdl.hunter.service.UserSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSlotServiceImpl implements UserSlotService {

    @Autowired
    UserSlotRepository userSlotRepository;

    @Override
   public UserSlotModel createSlot(UserSlotModel userSlot){
       UserSlot userSlotEntity=new UserSlot();
       userSlotEntity.setDesignation(userSlot.getDesignation());
       userSlotEntity.setEmployeeId(userSlot.getEmployeeId());
       userSlotEntity.setSpeciality(userSlot.getSpeciality());
       userSlotEntity.setInterviewDate(userSlot.getInterviewDate());
       userSlotRepository.save(userSlotEntity);
       return userSlot;
    }
}
