package com.dtdl.hunter.service;

import com.dtdl.hunter.model.UserSlotModel;

public interface UserSlotService {
    UserSlotModel createSlot(UserSlotModel userSlot);

    UserSlotModel updateSlot(UserSlotModel userSlotModel);

    void deleteSlot(Long id);
}
