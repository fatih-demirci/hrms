package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.WorkingTime;

public interface WorkingTimeDao extends JpaRepository<WorkingTime, Integer>{
	WorkingTime findById(int id);
}
