package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.EmailUpdateService;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.EmailUpdate;

@RestController
@RequestMapping("/EmailUpdate")
@CrossOrigin
public class EmailUpdateController {
	
	EmailUpdateService emailUpdateService;
	
	@Autowired
	public EmailUpdateController(EmailUpdateService emailUpdateService) {
		this.emailUpdateService=emailUpdateService;
	}
	
	@PostMapping("/update")
	public Result update(@RequestParam int employerId, EmailUpdate emailUpdate) {
		return emailUpdateService.update(employerId, emailUpdate);
	}
	
	@PostMapping("/approve")
	public Result approve(int employerId) {
		return emailUpdateService.approve(employerId);
	}
}
