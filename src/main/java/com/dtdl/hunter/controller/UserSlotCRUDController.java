package com.dtdl.hunter.controller;

import com.dtdl.hunter.model.UserSlotModel;
import com.dtdl.hunter.service.UserSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserSlotCRUDController {

    @Autowired
    UserSlotService userSlotService;

    @RequestMapping(value = "v1/createSlot",method = RequestMethod.POST)
    @ResponseBody
    public UserSlotModel createSlot(@RequestBody UserSlotModel userSlotModel){
       return userSlotService.createSlot(userSlotModel);
    }

    @RequestMapping(value = "v1/updateSlot",method = RequestMethod.PATCH)
    @ResponseBody
    public UserSlotModel updateSlot(@RequestBody UserSlotModel userSlotModel){
        return userSlotService.updateSlot(userSlotModel);
    }

    @RequestMapping(value = "v1/deleteSlot/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteSlot(@PathVariable Long id){
         userSlotService.deleteSlot(id);
    }

    //empty not handeled
    @RequestMapping(value = "v1/getSlotsForSpeciality",method = RequestMethod.GET)
    @ResponseBody
    public List<UserSlotModel> getSlotsForSpeciality(@RequestParam(value="speciality") String speciality){
        return userSlotService.getSlotsForSpeciality(speciality);
    }

    @RequestMapping(value = "v1/getMyAvailableSlots",method = RequestMethod.GET)
    @ResponseBody
    public List<UserSlotModel> getMyAvailableSlots(@RequestParam(value="employeeId") String employeeId){
        return userSlotService.getMyAvailableSlots(employeeId);
    }
}
