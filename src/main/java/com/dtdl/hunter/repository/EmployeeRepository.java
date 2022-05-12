package com.dtdl.hunter.repository;

import com.dtdl.hunter.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

   Employee findByEmailId(String employeeEmailId);
}
