package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.hrms.entities.conretes.JobSeeker;
import com.kodlamaio.hrms.entities.conretes.Member;

public interface JobSeekerDao extends JpaRepository<JobSeeker, Integer>{

	//@Query(value = "SELECT * FROM uyeler WHERE e_posta = ?1", nativeQuery = true)
	JobSeeker findByMember_eMail(String eMail);
	JobSeeker findByNationalIdentity(String nationalIdentity);
}
