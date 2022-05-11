package com.dtdl.hunter.service;

import com.dtdl.hunter.model.UserSlotModel;

import java.util.List;

public interface UserSlotService {
    UserSlotModel createSlot(UserSlotModel userSlot);

    UserSlotModel updateSlot(UserSlotModel userSlotModel);

    void deleteSlot(Long id);

    List<UserSlotModel> getSlotsForSpeciality(String speciality);

    List<UserSlotModel> getMyAvailableSlots(String employeeId);
}
