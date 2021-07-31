package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

public interface PhoneNumberDao extends JpaRepository<PhoneNumber, Integer>{

	PhoneNumber findByPhoneNumber(String phoneNumber);
	PhoneNumber findById(int id);
	PhoneNumber findByEmployer_IdAndExPhoneNumberId(int employerId,int exPhoneNumberId);
	Result deleteById(int id);
}
