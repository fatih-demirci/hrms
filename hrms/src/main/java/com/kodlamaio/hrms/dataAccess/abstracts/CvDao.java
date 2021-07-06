package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.Cv;

public interface CvDao extends JpaRepository<Cv, Integer>{
	List<Cv> findById(int id);
}
