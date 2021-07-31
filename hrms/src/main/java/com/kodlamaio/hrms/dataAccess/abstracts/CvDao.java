package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.dtos.CvWithJobSeekerDto;

public interface CvDao extends JpaRepository<Cv, Integer>{
	
	List<Cv> findById(int id);
	
	@Query("SELECT new com.kodlamaio.hrms.entities.dtos.CvWithJobSeekerDto"
			+ "(j.id,j.name, j.lastName ,j.birthDay, c.id, m.eMail, i.imageURL)"
			+ "From JobSeeker j Inner Join j.cv c Inner Join j.member m Left Join c.image i")
	List<CvWithJobSeekerDto> cvWithJobSeekerDetails();
	

}
