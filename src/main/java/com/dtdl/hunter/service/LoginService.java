package com.dtdl.hunter.service;

import com.dtdl.hunter.entity.Employee;
import org.springframework.web.bind.annotation.RequestParam;

public interface LoginService {

     Employee getEmployee(String employeeEmail);
}
