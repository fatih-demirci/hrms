package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.CvService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.dtos.CvWithJobSeekerDto;

@RestController
@RequestMapping("/api/Cv")
@CrossOrigin
public class CvController {
	
	CvService cvService;
	
	@Autowired
	public CvController(CvService cvService) {
		this.cvService=cvService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<Cv>> getAll(){
		return cvService.getAll();
	}
	
	@GetMapping("/cvWithJobSeekerDetails")
	public DataResult<List<CvWithJobSeekerDto>> cvWithJobSeekerDetails(){
		return cvService.cvWithJobSeekerDetails();
	}

}
