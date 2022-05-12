package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.model.EmployeeModel;
import com.dtdl.hunter.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {

    private Map<String,EmployeeModel> mailIdVsEmployee=new HashMap<>();
    @Override
   public void addSessionToCache(EmployeeModel employee){
       mailIdVsEmployee.put(employee.getEmailId(),employee);
    }

    @Override
    public EmployeeModel getEmployee(String mailId){
       return mailIdVsEmployee.get(mailId);
    }

}
