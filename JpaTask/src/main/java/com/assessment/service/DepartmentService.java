package com.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assessment.exception.NoContentFoundException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.model.Department;
import com.assessment.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> findAllDepartments() throws NoContentFoundException {
		List<Department> departments = departmentRepository.findAll();
		if (departments.isEmpty()) {
			throw new NoContentFoundException("No any Department found");
		}
		return departments;
	}
	
	public Department findDepartmentById(long deptId) throws ResourceNotFoundException {
		return departmentRepository.findById(deptId)
			.orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + deptId));
	}
	
	public Department addDepartment(Department department) {
		return departmentRepository.save(department);
	}
	
	public Department updateDepartmentById(long deptId, Department department) throws ResourceNotFoundException {
		Department existingDepartment = departmentRepository.findById(deptId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + deptId));
		existingDepartment.setDeptName(department.getDeptName());
		existingDepartment.setEmployees(department.getEmployees());
		return departmentRepository.save(existingDepartment);
	}
	
	public ResponseEntity<?> deleteDepartmentById(long deptId) throws ResourceNotFoundException {
		departmentRepository.findById(deptId)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + deptId));
		departmentRepository.deleteById(deptId);
		return ResponseEntity.noContent().build();
	}

}
