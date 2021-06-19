package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.JobSeekerService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseeker")
public class JobSeekerController {

	JobSeekerService jobSeekerService;
	
	public JobSeekerController(JobSeekerService jobSeekerService) {
		this.jobSeekerService=jobSeekerService;
	}
	
	@GetMapping("/getall")
	DataResult<List<JobSeeker>> getAll(){
		return jobSeekerService.getall();
	}
	
	@PostMapping
	Result add(@RequestBody JobSeeker jobSeeker) {
		return jobSeekerService.add(jobSeeker);
	}
}

