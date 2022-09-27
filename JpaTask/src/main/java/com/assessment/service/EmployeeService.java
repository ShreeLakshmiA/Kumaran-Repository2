package com.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assessment.exception.NoContentFoundException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.model.Employee;
import com.assessment.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAllEmployees() throws NoContentFoundException {
		List<Employee> employees = employeeRepository.findAll();
		if (employees.isEmpty()) {
			throw new NoContentFoundException("No any Employee found");
		}
		return employees;
	}
	
	public Employee findEmployeeById(String empId) throws ResourceNotFoundException {
		return employeeRepository.findById(empId)
			.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + empId));
	}
	
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployeeById(String empId, Employee employee) throws ResourceNotFoundException {
		Employee existingEmployee = employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + empId));
		existingEmployee.setEmpName(employee.getEmpName());
		existingEmployee.setAddresses(employee.getAddresses());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setPhoneNumber(employee.getPhoneNumber());
		existingEmployee.setProjects(employee.getProjects());
		return employeeRepository.save(existingEmployee);
	}
	
	public ResponseEntity<?> deleteEmployeeById(String empId) throws ResourceNotFoundException {
		employeeRepository.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + empId));
		employeeRepository.deleteById(empId);
		return ResponseEntity.noContent().build();
	}

}
