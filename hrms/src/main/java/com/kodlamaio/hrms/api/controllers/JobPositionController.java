package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.JobPositionService;
import com.kodlamaio.hrms.entities.conretes.JobPosition;

@RestController
@RequestMapping("/api/jobposition")
public class JobPositionController {

	private JobPositionService jobPositionService;
	
	@Autowired
	private JobPositionController(JobPositionService jobPositionService){
		super();
		this.jobPositionService=jobPositionService;
	}
	
	@GetMapping("/getall")
	public List<JobPosition> gelAll(){
		return this.jobPositionService.getAll();
	}
}
