package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.WorkingTimeService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.WorkingTime;

@RestController
@RequestMapping("/api/WorkingTime")
@CrossOrigin
public class WorkingTimeController {

	WorkingTimeService workingTimeService;
	
	@Autowired
	public WorkingTimeController(WorkingTimeService workingTimeService) {
		this.workingTimeService=workingTimeService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<WorkingTime>> getAll(){
		return workingTimeService.getAll();
	}
}
