package com.kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.TypeOfWorkService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.TypeOfWork;

@RestController
@RequestMapping("api/TypeOfWork")
@CrossOrigin
public class TypeOfWorkController {

	TypeOfWorkService typeOfWorkService;
	
	public TypeOfWorkController(TypeOfWorkService typeOfWorkService) {
		this.typeOfWorkService=typeOfWorkService;
	}
	@GetMapping("/getAll")
	public DataResult<List<TypeOfWork>> getAll(){
		return typeOfWorkService.getAll();
	}
}
