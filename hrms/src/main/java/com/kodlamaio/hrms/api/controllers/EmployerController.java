package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.EmployerService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.entities.conretes.Employer;

@RestController
@RequestMapping("/api/employer")
public class EmployerController {

	EmployerService employerService;
	
	@Autowired
	public EmployerController(EmployerService employerService) {
		this.employerService=employerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Employer>> getall(){
		return employerService.getAll();
	}
	
	@PostMapping
	public Result add(@RequestBody Employer employer) {
		return employerService.add(employer);
	}
	
	@GetMapping("/getAllJobAdvertisement")
	public Result getAllJobAdvertisement() {
		return employerService.getAllJobAdvertisements(null);
	}
	
	@GetMapping("/getAllJobAdvertisementSorted")
	public Result getAllJobAdvertisementSorted() {
		return employerService.getAllJobAdvertisementSorted();
	}
	
	@PostMapping("/getAllEmployersJobAdvertisements")
	public DataResult<Employer> getAllEmployersJobAdvertisements(@RequestParam int id){
		return employerService.getAllEmployersJobAdvertisements(id);
	}
}
