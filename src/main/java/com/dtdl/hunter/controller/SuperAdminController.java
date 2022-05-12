package com.dtdl.hunter.controller;

import com.dtdl.hunter.model.TalentAquisitionDto;
import com.dtdl.hunter.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SuperAdminController {


    @Autowired
    private SuperAdminService service;

    @GetMapping(value = "v1/getTalentAcuisitionData")
    public ResponseEntity<List<TalentAquisitionDto>> getTalentAcuisitionData(){
        List<TalentAquisitionDto> list  = service.getTalentAquisitionData();

        return ResponseEntity.ok(list);
    }

}
