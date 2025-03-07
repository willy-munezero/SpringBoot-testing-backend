package com.auca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auca.Model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
 

}
