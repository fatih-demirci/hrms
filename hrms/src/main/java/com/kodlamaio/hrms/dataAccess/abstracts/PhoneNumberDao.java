package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Integer>{

	PhoneNumber findByPhoneNumber(String phoneNumber);
}
