package com.kodlamaio.hrms.api.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.WebsiteService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Website;

@RestController
@RequestMapping("/WebsiteController")
@CrossOrigin
public class WebsiteController {
	WebsiteService websiteService;
	
	public WebsiteController(WebsiteService websiteService) {
		this.websiteService=websiteService;
	}
	
	@GetMapping("/isUpdate")
	public DataResult<Website> isUpdate(@RequestParam int id) {
		return websiteService.isUpdate(id);
	}
	
	@PostMapping("/approve")
	public Result approve(int employerId,int websiteId) {
		return websiteService.approve(employerId, websiteId);
	}
}
