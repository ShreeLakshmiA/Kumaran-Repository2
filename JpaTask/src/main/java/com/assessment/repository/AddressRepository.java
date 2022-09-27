package com.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.Address;
import com.assessment.model.AddressId;

@Repository
public interface AddressRepository extends JpaRepository<Address, AddressId> {

}
