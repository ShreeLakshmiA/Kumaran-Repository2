package com.assessment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assessment.exception.NoContentFoundException;
import com.assessment.exception.ResourceNotFoundException;
import com.assessment.model.Project;
import com.assessment.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public List<Project> findAllProjects() throws NoContentFoundException {
		List<Project> projects = projectRepository.findAll();
		if (projects.isEmpty()) {
			throw new NoContentFoundException("No any Project found");
		}
		return projects;
	}
	
	public Project findProjectById(long projectId) throws ResourceNotFoundException {
		return projectRepository.findById(projectId)
			.orElseThrow(() -> new ResourceNotFoundException("Project not found with id : " + projectId));
	}
	
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}
	
	public Project updateProjectById(long projectId, Project project) throws ResourceNotFoundException {
		Project existingProject = projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id : " + projectId));
		existingProject.setProjectName(project.getProjectName());
		existingProject.setEmployees(project.getEmployees());
		return projectRepository.save(existingProject);
	}
	
	public ResponseEntity<?> deleteProjectById(long projectId) throws ResourceNotFoundException {
		projectRepository.findById(projectId)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found with id : " + projectId));
		projectRepository.deleteById(projectId);
		return ResponseEntity.noContent().build();
	}

}
