package com.kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.EmployerService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
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
	public ResponseEntity<?> add(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(employerService.add(employer));
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
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors =new HashMap<String,String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		return errors;
	}
}
