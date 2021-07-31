package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.SystemStaff;

public interface SystemStaffDao extends JpaRepository<SystemStaff, Integer>{
	SystemStaff findById(int id);
}
