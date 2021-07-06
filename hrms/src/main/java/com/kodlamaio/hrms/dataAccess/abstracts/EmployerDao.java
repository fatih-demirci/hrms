package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	//@Query(value= "SELECT * FROM uyeler WHERE e_posta = ?1", nativeQuery = true)
	Employer findByMember_eMail(String eMail);
	Employer findByPhoneNumbers_phoneNumber(String phoneNumber);
	Employer findById(int id);
	
	@Query("SELECT new com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto"
			+ "(e.id, e.companyName,a.openPositions, a.applicationDeadline, a.releaseDate, a.jobPosition.name)"
			+ " From Employer e Inner Join e.jobAdvertisements a")
	List<EmployerWithAdvertisementDto> getEmployerWithAdvertisementDetails();
	
	//private int id;-
	//private String companyName; -
	//private int openPositions;-
	//private Date applicationDeadline;-
	//private Date releaseDate;-
	//private String positionName;
}

