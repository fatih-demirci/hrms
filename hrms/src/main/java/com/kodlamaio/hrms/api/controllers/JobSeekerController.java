package com.kodlamaio.hrms.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.JobSeekerService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

@RestController
@RequestMapping("/api/jobseeker")
@CrossOrigin
public class JobSeekerController {

	JobSeekerService jobSeekerService;

	public JobSeekerController(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}

	@GetMapping("/getall")
	DataResult<List<JobSeeker>> getAll() {
		return jobSeekerService.getall();
	}
	
	@GetMapping("/getJobSeekerById")
	public DataResult<JobSeeker> getJobSeekerById(@RequestParam int id){
		return jobSeekerService.getJobSeekerById(id);
	}

	@PostMapping("add")
	ResponseEntity<?> add(@Valid @RequestBody JobSeeker jobSeeker) {
		return ResponseEntity.ok(jobSeekerService.add(jobSeeker));
	}

	@PostMapping("/addCv")
	public ResponseEntity<?> addCv(@Valid @RequestParam int jobSeekerId,@Valid @RequestBody Cv cv) {
		return ResponseEntity.ok(jobSeekerService.addCv(jobSeekerId, cv));
	}
	
	@PostMapping("/getCv")
	ResponseEntity<?> getAllCv(@Valid @RequestParam int jobSeekerId){
		return ResponseEntity.ok(jobSeekerService.getCv(jobSeekerId));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}

}
