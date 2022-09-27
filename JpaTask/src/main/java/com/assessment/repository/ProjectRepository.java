package com.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
