package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.CompanyNameUpdateService;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.CompanyNameUpdate;

@RestController
@RequestMapping(name="/CompanyNameUpdateController")
@CrossOrigin
public class CompanyNameUpdateController {
	CompanyNameUpdateService companyNameUpdateService;
	
	@Autowired
	public CompanyNameUpdateController(CompanyNameUpdateService companyNameUpdateService) {
		this.companyNameUpdateService=companyNameUpdateService;
	}
	
	
	@PostMapping("/update")
	public Result update(@RequestParam int employerId , CompanyNameUpdate companyNameUpdate) {
		return companyNameUpdateService.update(employerId, companyNameUpdate);
	}
	
	@PostMapping("/approve")
	public Result approve(@RequestParam int employerId) {
		return companyNameUpdateService.approve(employerId);
	}
	
}
