package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.CityService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.City;

@RestController
@RequestMapping("/api/City")
@CrossOrigin
public class CityController {
	
	CityService cityService;
	
	@Autowired
	public CityController(CityService cityService) {
		this.cityService=cityService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<City>> getAll(){
		return cityService.getAll();
	}
}
