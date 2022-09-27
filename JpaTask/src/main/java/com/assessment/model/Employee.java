package com.assessment.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.assessment.generator.EmployeeIdGenerator;

@Entity
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_sequence_generator")
	@GenericGenerator(name = "employee_sequence_generator",
			strategy = "com.assessment.generator.EmployeeIdGenerator",
			parameters = {
					@Parameter(name = EmployeeIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = EmployeeIdGenerator.VALUE_PREFIX_PARAMETER, value = "KUM"),
					@Parameter(name = EmployeeIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d"),
					@Parameter(name = EmployeeIdGenerator.SEQUENCE_PARAM, value = "employee_sequence")
			})
	@Column(name = "emp_id")
	private String empId;
	
	@Column(name = "emp_name")
	private String empName;
	
	@OneToMany(mappedBy = "employee", targetEntity = Address.class, cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id", referencedColumnName = "dept_id", foreignKey = @ForeignKey(name = "FK_Employee_Department"))
	private Department department;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(foreignKey = @ForeignKey(name = "FK_Employee_PhoneNumber"), value = {
        @JoinColumn(name="country_code", referencedColumnName="country_code"),
        @JoinColumn(name="mobile_number", referencedColumnName="mobile_number")
    })
	private PhoneNumber phoneNumber;
	
	@ManyToMany(mappedBy = "employees", fetch = FetchType.EAGER)
	private Set<Project> projects;

	public Employee() {
		super();
		this.addresses = new ArrayList<>();
		this.projects = new HashSet<>();
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empId == null) ? 0 : empId.hashCode());
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
		Employee other = (Employee) obj;
		if (empId == null) {
			if (other.empId != null)
				return false;
		} else if (!empId.equals(other.empId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [" + (empId != null ? "empId=" + empId + ", " : "")
				+ (empName != null ? "empName=" + empName + ", " : "")
				+ (addresses != null ? "addresses=" + addresses + ", " : "")
				+ (department != null ? "department=" + department + ", " : "")
				+ (phoneNumber != null ? "phoneNumber=" + phoneNumber + ", " : "")
				+ (projects != null ? "projects=" + projects : "") + "]";
	}
	

}
