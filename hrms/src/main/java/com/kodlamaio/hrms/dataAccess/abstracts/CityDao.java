package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.City;

public interface CityDao extends JpaRepository<City, Integer>{
	
	City findById(int id);

}
