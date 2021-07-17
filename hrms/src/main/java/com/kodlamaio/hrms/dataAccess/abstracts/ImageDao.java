package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.Image;

public interface ImageDao extends JpaRepository<Image, Integer>{
	Image findById(int id);
	void deleteById(int id);
}
