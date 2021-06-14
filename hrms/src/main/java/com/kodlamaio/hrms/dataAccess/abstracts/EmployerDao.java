package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.Member;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	//@Query(value= "SELECT * FROM uyeler WHERE e_posta = ?1", nativeQuery = true)
	Employer findByMember_eMail(String eMail);
	
}
