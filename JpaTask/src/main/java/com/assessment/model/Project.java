package com.assessment.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(generator = "project-sequence-generator")
	@GenericGenerator(name = "project-sequence-generator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "project_sequence"),
					@Parameter(name = "initial_value", value = "1001"),
					@Parameter(name = "increment_size", value = "1")
			})
	@Column(name = "project_id")
	private long projectId;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Transient
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "employee_project", joinColumns = @JoinColumn(name = "emp_id"), inverseJoinColumns = @JoinColumn(name = "project_id"),
			foreignKey = @ForeignKey(name = "FK_Project_Employee"),inverseForeignKey = @ForeignKey(name = "FK_Project_Project"))
	private Set<Employee> employees;

	public Project() {
		super();
		this.employees = new HashSet<>();
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (projectId ^ (projectId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (projectId != other.projectId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", "
				+ (projectName != null ? "projectName=" + projectName + ", " : "")
				+ (employees != null ? "employees=" + employees : "") + "]";
	}

}
