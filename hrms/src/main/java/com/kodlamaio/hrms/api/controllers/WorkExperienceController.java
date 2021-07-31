package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.WorkExperienceService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.WorkExperience;

@RestController
@RequestMapping("/api/WorkExperienceController")
@CrossOrigin
public class WorkExperienceController {
	
	WorkExperienceService workExperienceService;
	
	public WorkExperienceController(WorkExperienceService workExperienceService) {
		this.workExperienceService=workExperienceService;
	}
	
	@GetMapping("getAllWorkExperienceSorted")
	public DataResult<List<WorkExperience>> getAllWorkExperienceSorted(@RequestParam int id){
		return this.workExperienceService.getAllWorkExperienceSorted(id);
	}
}
