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

import com.assessment.model.Department;
import com.assessment.service.DepartmentService;

@RestController
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping("/departments")
	public ResponseEntity<List<Department>> findAllDepartments() {
		List<Department> departments = departmentService.findAllDepartments();
		return new ResponseEntity<>(departments, HttpStatus.OK);
	}
	
	@GetMapping("/departments/{deptId}")
	public ResponseEntity<Department> findDepartmentById(@PathVariable("deptId") long deptId) {
		Department department = departmentService.findDepartmentById(deptId);
		return new ResponseEntity<>(department, HttpStatus.OK);
	}
	
	@PostMapping("/departments")
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		try {
			Department savedDepartment = departmentService.addDepartment(department);
			return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/departments/{deptId}")
	public ResponseEntity<Department> updateDepartmentById(@PathVariable("deptId") long deptId, @RequestBody Department department) {
		Department updatedDepartment = departmentService.updateDepartmentById(deptId, department);
		return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
	}
	
	@PutMapping("/departments")
	public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
		Department updatedDepartment = departmentService.updateDepartmentById(department.getDeptId(), department);
		return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
	}
	
	@DeleteMapping("/departments/{deptId}")
	public ResponseEntity<?> deleteDepartmentById(@PathVariable("deptId") long deptId) {
		return departmentService.deleteDepartmentById(deptId);
	}

}
