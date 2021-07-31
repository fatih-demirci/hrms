package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;


public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	
	JobAdvertisement findById(int id);
	List<JobAdvertisement> findAllByIsJobAdvertisementOpenAndApproved(boolean isOpen,boolean approved,Pageable pageable);
	Long countByIsJobAdvertisementOpenAndApproved(boolean isOpen,boolean approved);
	
    @Query("SELECT count(id) FROM JobAdvertisement where isJobAdvertisementOpen=:isOpen AND approved=:approved AND (:city='*' OR city.city=:city) AND (:typeOfWork='*' OR typeOfWork.typeOfWork=:typeOfWork)")
	Long countByIsOpenAndApprovedAndCityAndTypeOfWork(@Param("isOpen") boolean isOpen,@Param("approved") boolean approved,@Param("city") String city,@Param("typeOfWork") String typeOfWork);

    Result deleteById(int jobAdvertisementId);

}
