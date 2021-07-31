package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Website;

public interface WebsiteDao extends JpaRepository<Website, Integer>{
	Website findById(int id);
	Website findByExWebsiteId(int id);
	Website findByWebsite(String website);
	Result deleteById(int id);
}
