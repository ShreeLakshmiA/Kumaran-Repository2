package com.assessment.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	
	@EmbeddedId
	private AddressId addressId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "emp_id", referencedColumnName = "emp_id", 
		foreignKey = @ForeignKey(name = "FK_Address_Employee"))
	private Employee employee;

	public Address() {
		super();
	}

	public Address(AddressId addressId, String city, String country) {
		super();
		this.addressId = addressId;
		this.city = city;
		this.country = country;
	}

	public AddressId getAddressId() {
		return addressId;
	}

	public void setAddressId(AddressId addressId) {
		this.addressId = addressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressId == null) ? 0 : addressId.hashCode());
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
		Address other = (Address) obj;
		if (addressId == null) {
			if (other.addressId != null)
				return false;
		} else if (!addressId.equals(other.addressId))
			return false;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [" + (addressId != null ? "addressId=" + addressId + ", " : "")
				+ (city != null ? "city=" + city + ", " : "") + (country != null ? "country=" + country + ", " : "")
				+ (employee != null ? "employee=" + employee : "") + "]";
	}
	

}
