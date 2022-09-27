package com.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.Employee;
import com.assessment.service.EmployeeService;

@RestController
public class EmpolyeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> findAllEmployees() {
		List<Employee> employees = employeeService.findAllEmployees();
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/employees/{empId}")
	public ResponseEntity<Employee> findDepartmentById(@PathVariable("empId") String empId) {
		Employee employee = employeeService.findEmployeeById(empId);
		return new ResponseEntity<>(employee, HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		try {
			Employee savedEmployee = employeeService.addEmployee(employee);
			return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/employees/{empId}")
	public ResponseEntity<Employee> updateEmployeeById(@PathVariable("empId") String empId, @RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployeeById(empId, employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployeeById(employee.getEmpId(), employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping("/employees/{empId}")
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("empId") String empId) {
		return employeeService.deleteEmployeeById(empId);
	}

}
