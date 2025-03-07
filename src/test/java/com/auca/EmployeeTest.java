package com.auca;

//Author Willy
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.auca.Model.Employee;
import com.auca.Repository.EmployeeRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeTest {
  
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for save employee
	@Test
	@Rollback(false)
	@Order(1)
	public void testCreateEmployee() {
		
		Employee employee = new Employee();
		employee.setFirstName("Gisagara");
		employee.setId(8);
		employee.setLastName("Alida");
		employee.setEmail("alida@gmail.com");
		employeeRepository.save(employee);
		
		Employee savedEmployee = employeeRepository.save(employee);
		
		assertNotNull(savedEmployee);
		
	}
	

	// Get all employees List 
	@Test
	@Order(3)
	public void testListEmployees() {
		
			List<Employee> employee = (List<Employee>)employeeRepository.findAll();
			
			for (Employee employees : employee) {
				
				System.out.println(employees);
				
			}
		assertThat(employee).size().isGreaterThan(0);
		
	}
	
	//get employees by using the ID
	
	@Test
	@Order(2)
	public void testGetEmployee() {
		Employee employee = employeeRepository.findById(6L).get();
		
		Assertions.assertThat(employee.getId()).isEqualTo(6L);
		
		
	}
	
	//Test Method to update the employee in the database
	@Test
	@Order(4)
	@Rollback(false)
	public void TestUpdateEmployee() {
		Employee employee = employeeRepository.findById(1L).get();
		
		employee.setEmail("munezero@gmail.com");
		employee.setLastName("Willy");
		
	    Employee employeeUpdated = employeeRepository.save(employee);
		
		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("munezero@gmail.com");
		Assertions.assertThat(employeeUpdated.getLastName()).isEqualTo("Willy");
		
	}
	
	@Test
	@Rollback(false)
	@Order(5)
	public void testDeleteEmployee() {
		long id = 7;
		
		boolean isExistBeforeDelete = employeeRepository.findById(id).isPresent();
		
		employeeRepository.deleteById(id);
		
		boolean notExistAfterDelete = employeeRepository.findById(id).isPresent();
		
		assertTrue(isExistBeforeDelete);
		assertFalse(notExistAfterDelete);
		
		
	}
}
