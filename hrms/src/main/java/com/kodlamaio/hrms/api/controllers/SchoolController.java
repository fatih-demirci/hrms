package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.SchoolService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.School;

@RestController
@RequestMapping("/api/SchoolController")
@CrossOrigin
public class SchoolController {

	SchoolService schoolService;

	@Autowired
	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@PostMapping("/getAllSchoolSorted")
	public DataResult<List<School>> getAllSchoolSorted(@RequestParam int id) {
		return schoolService.getAllSchoolSorted(id);
	}

}