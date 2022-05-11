package com.dtdl.hunter.controller;

import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.service.UserSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserSlotCRUDController {

    @Autowired
    UserSlotService userSlotService;

    @RequestMapping(value = "v1/createSlot",method = RequestMethod.POST)
    @ResponseBody
    public UserSlotModel createSlot(@RequestBody UserSlotModel userSlotModel){
        userSlotModel.setInterviewDate(new Date());
       return userSlotService.createSlot(userSlotModel);
    }
}
