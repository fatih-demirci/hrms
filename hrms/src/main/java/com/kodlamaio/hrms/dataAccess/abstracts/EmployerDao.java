package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	//@Query(value= "SELECT * FROM uyeler WHERE e_posta = ?1", nativeQuery = true)
	Employer findByMember_eMail(String eMail);
	Employer findByPhoneNumbers_phoneNumber(String phoneNumber);
	Employer findById(int id);
	
}
