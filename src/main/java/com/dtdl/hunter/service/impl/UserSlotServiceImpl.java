package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.UserSlot;
import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.repository.UserSlotRepository;
import com.dtdl.hunter.service.UserSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
       userSlotEntity = userSlotRepository.save(userSlotEntity);
       userSlot.setId(userSlotEntity.getId());
       return userSlot;
    }

    @Override
    public UserSlotModel updateSlot(UserSlotModel userSlotModel) {
        Optional<UserSlot> userSlotEntity = userSlotRepository.findById(userSlotModel.getId());
        userSlotEntity.get().setInterviewDate(userSlotModel.getInterviewDate());
        userSlotRepository.save(userSlotEntity.get());
        userSlotModel.setEmployeeId(userSlotEntity.get().getEmployeeId());
        userSlotModel.setDesignation(userSlotEntity.get().getDesignation());
        userSlotModel.setSpeciality(userSlotEntity.get().getSpeciality());
        return userSlotModel;
    }

    @Override
    public void deleteSlot(Long id) {
        userSlotRepository.deleteById(id);
    }
}
