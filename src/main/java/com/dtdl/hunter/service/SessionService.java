package com.dtdl.hunter.service;

import com.dtdl.hunter.model.EmployeeModel;

public interface SessionService {
    void addSessionToCache(EmployeeModel employee);

    EmployeeModel getEmployee(String mailId);
}
