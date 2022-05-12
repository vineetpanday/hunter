package com.dtdl.hunter.model;

import com.dtdl.hunter.entity.Employee;
import lombok.Data;

@Data
public class EmployeeModel {

    private String userId;
    private String emailId;
    private String name;
    private String designation;
    private String speciality;

    public EmployeeModel(Employee employee) {
      userId=employee.getUserId();
       emailId=employee.getEmailId();
        name=employee.getName();
       designation=employee.getDesignation();
       speciality=employee.getSpeciality();

    }
}
