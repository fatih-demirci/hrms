package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;

public interface EmployerDao extends JpaRepository<Employer, Integer>{
	
	//@Query(value= "SELECT * FROM uyeler WHERE e_posta = ?1", nativeQuery = true)
	Employer findByMember_eMail(String eMail);
	Employer findByPhoneNumbers_phoneNumber(String phoneNumber);
	Employer findById(int id);
	
	@Query("SELECT new com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto"
			+ "(a.id,e.id, e.companyName,a.openPositions, a.applicationDeadline, a.releaseDate, a.jobPosition.name, a.isJobAdvertisementOpen, a.approved)"
			+ "From Employer e Inner Join e.jobAdvertisements a WHERE a.isJobAdvertisementOpen=:isOpen AND a.approved=:approved")
	List<EmployerWithAdvertisementDto> getEmployerWithAdvertisementDetails(@Param("isOpen") boolean isOpen, @Param("approved") boolean approved);
	
}

