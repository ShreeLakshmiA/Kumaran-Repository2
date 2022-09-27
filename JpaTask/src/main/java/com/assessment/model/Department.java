package com.assessment.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "department", uniqueConstraints = @UniqueConstraint(name = "UK_dept_name", columnNames = {"dept_name"}))
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private long deptId;
	
	@Column(name = "dept_name")
	private String deptName;
	
	@OneToMany(mappedBy = "department", targetEntity = Employee.class, fetch = FetchType.LAZY)
	@Transient
	private Set<Employee> employees;

	public Department() {
		super();
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
		result = prime * result + (int) (deptId ^ (deptId >>> 32));
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
		Department other = (Department) obj;
		if (deptId != other.deptId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", " + (deptName != null ? "deptName=" + deptName + ", " : "")
				+ (employees != null ? "employees=" + employees : "") + "]";
	}

}
