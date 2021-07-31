package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.SystemStaffService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.SystemStaff;

@CrossOrigin
@RestController
@RequestMapping("/SystemStaff")
public class SystemStaffController {
	SystemStaffService systemStaffService;
	
	@Autowired
	public SystemStaffController(SystemStaffService systemStaffService) {
		this.systemStaffService=systemStaffService;
	}
	
	@PostMapping("/add")
	Result add(@RequestBody SystemStaff systemStaff) {
		return systemStaffService.add(systemStaff);
	}
	
	@GetMapping("/getSystemStaff")
	public DataResult<SystemStaff> getSystemStaff(int id){
		return systemStaffService.getSystemStaff(id);
	}

}
