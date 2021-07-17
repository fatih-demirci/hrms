package com.kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;

	

@RestController
@RequestMapping("/api/jobadvertisement")
@CrossOrigin
public class JobAdvertisementController {
	private JobAdvertisementService jobAdvertisementService;
	
	public JobAdvertisementController(JobAdvertisementService jobAdvertisementService) {
		this.jobAdvertisementService=jobAdvertisementService;
	}
	
	@PostMapping("/jobAdvertisementOpen")
	Result jobAdvertisementOpen(@RequestParam int jobAdvertisementId,@RequestParam boolean isJobAdvertisementOpen) {
		return jobAdvertisementService.jobAdvertisementOpen(jobAdvertisementId, isJobAdvertisementOpen);
	}
	
	@PostMapping("/addAdvertisement")
	Result addAdvertisement(@RequestBody JobAdvertisement jobAdvertisement, @RequestParam int employerId) {
		return jobAdvertisementService.addAdvertisement(jobAdvertisement, employerId);
	}
	
	@GetMapping("/getJobAdvertisementById")
	public DataResult<JobAdvertisement> getJobAdvertisementById(@RequestParam int id){
		return jobAdvertisementService.getJobAdvertisementById(id);
	}
	
	@PostMapping("/jobAdvertisementApprove")
	public Result jobAdvertisementApprove(int jobAdvertisementId,boolean isJobAdvertisementApprove) {
		return jobAdvertisementService.jobAdvertisementApprove(jobAdvertisementId, isJobAdvertisementApprove);
	}
}