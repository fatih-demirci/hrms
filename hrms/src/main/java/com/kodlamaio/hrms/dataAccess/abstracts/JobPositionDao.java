package com.kodlamaio.hrms.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.JobPosition;

public interface JobPositionDao extends JpaRepository<JobPosition, Integer>{
	
	//@Query(value = "SELECT * FROM is_pozisyonlari WHERE isim = ?1", nativeQuery = true)
	JobPosition findByName(String name);
	
	JobPosition findById(int id);
}
