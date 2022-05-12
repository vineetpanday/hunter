package com.dtdl.hunter.controller;

import com.dtdl.hunter.entity.Employee;
import com.dtdl.hunter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(name = "v1/login",method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployee(@RequestParam String emailId){

        Employee employee = loginService.getEmployee(emailId);
        return ResponseEntity.ok(employee);

    }


}
