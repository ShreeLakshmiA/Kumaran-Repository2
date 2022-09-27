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

import com.assessment.model.Project;
import com.assessment.service.ProjectService;

@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> findAllProjects() {
		List<Project> projects = projectService.findAllProjects();
		return new ResponseEntity<>(projects, HttpStatus.OK);
	}
	
	@GetMapping("/projects/{projectId}")
	public ResponseEntity<Project> findProjectById(@PathVariable("projectId") long projectId) {
		Project project = projectService.findProjectById(projectId);
		return new ResponseEntity<>(project, HttpStatus.OK);
	}
	
	@PostMapping("/projects")
	public ResponseEntity<Project> addProject(@RequestBody Project project) {
		try {
			Project savedProject = projectService.addProject(project);
			return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/projects/{projectId}")
	public ResponseEntity<Project> updateProjectById(@PathVariable("projectId") long projectId, @RequestBody Project project) {
		Project updatedProject = projectService.updateProjectById(projectId, project);
		return new ResponseEntity<>(updatedProject, HttpStatus.OK);
	}
	
	@PutMapping("/projects")
	public ResponseEntity<Project> updateProject(@RequestBody Project project) {
		Project updatedProject = projectService.updateProjectById(project.getProjectId(), project);
		return new ResponseEntity<>(updatedProject, HttpStatus.OK);
	}
	
	@DeleteMapping("/projects/{projectId}")
	public ResponseEntity<?> deleteProjectById(@PathVariable("projectId") long projectId) {
		return projectService.deleteProjectById(projectId);
	}

}
