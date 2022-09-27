package com.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.model.PhoneNumber;
import com.assessment.model.PhoneNumberId;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, PhoneNumberId> {

}
