package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.UserSlot;
import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.repository.UserSlotRepository;
import com.dtdl.hunter.service.UserSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Calendar cal = Calendar.getInstance();
        cal.setTime(userSlot.getInterviewDate());
        cal.add(Calendar.HOUR, -5);
        cal.add(Calendar.MINUTE, -30);
        Date oneHourBack = cal.getTime();
       userSlotEntity.setInterviewDate(oneHourBack);
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

    @Override
    public List<UserSlotModel> getSlotsForSpeciality(String speciality) {
        List<UserSlot> userSlots = userSlotRepository.findAllBySpecialityAndInterviewDateAfterNow(speciality);
       return userSlots.stream().map(slot->new UserSlotModel(slot.getId(),slot.getEmployeeId(),slot.getInterviewDate(),slot.getDesignation(),slot.getSpeciality())).collect(Collectors.toList());
    }

    @Override
    public List<UserSlotModel> getMyAvailableSlots(String employeeId) {
        List<UserSlot> userSlots= userSlotRepository.getMyAvailableSlots(employeeId);
        return userSlots.stream().map(slot->new UserSlotModel(slot.getId(),slot.getEmployeeId(),slot.getInterviewDate(),slot.getDesignation(),slot.getSpeciality())).collect(Collectors.toList());
    }
}
