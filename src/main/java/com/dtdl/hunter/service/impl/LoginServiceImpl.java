package com.dtdl.hunter.service.impl;

import com.dtdl.hunter.entity.Employee;
import com.dtdl.hunter.model.EmployeeModel;
import com.dtdl.hunter.repository.EmployeeRepository;
import com.dtdl.hunter.service.LoginService;
import com.dtdl.hunter.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    SessionService sessionService;

    @Override
    public Employee getEmployee(String employeeEmailId) {

        Employee employee = employeeRepository.findByEmailId(employeeEmailId);
        sessionService.addSessionToCache(new EmployeeModel(employee));
        return employee;
    }
}
