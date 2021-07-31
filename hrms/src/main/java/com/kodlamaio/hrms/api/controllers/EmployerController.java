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
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;
import com.kodlamaio.hrms.entities.conretes.Website;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;


@CrossOrigin
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
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody Employer employer) {
		return ResponseEntity.ok(employerService.add(employer));
	}
	
	@PostMapping("/updatePassword")
	public Result updatePassword(int employerId,String password,String passwordRepeat) {
		return employerService.updatePassword(employerId, password, passwordRepeat);
	}
	
	@PostMapping("/updateWebsite")
	public Result updateWebsite(@RequestParam int employerId ,@RequestBody Website website) {
		return employerService.updateWebsite(employerId, website);
	}
	
	@PostMapping("/updatePhoneNumber")
	public Result updatePhoneNumber(@RequestParam int employerId,@RequestBody PhoneNumber phoneNumber) {
		return employerService.updatePhoneNumber(employerId, phoneNumber);
	}
	
	@GetMapping("/getAllJobAdvertisement")
	public DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetails(@RequestParam boolean isOpen, @RequestParam boolean approved,int pageNo, int pageSize) {
		//return employerService.getAllJobAdvertisements(null);
		return employerService.getEmployerWithAdvertisementDetails(isOpen,approved,pageNo,pageSize);
	}
	
	@GetMapping("/getEmployerWithAdvertisementDetailsByCityAndTypeOfWork")
	public DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(@RequestParam boolean isOpen,@RequestParam boolean approved,@RequestParam int pageNo,@RequestParam int pageSize, String city, String typeOfWork){
		return employerService.getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(isOpen, approved, pageNo, pageSize, city,typeOfWork);
	}																					
	
	@GetMapping("/getAllJobAdvertisementSorted")
	public Result getAllJobAdvertisementSorted() {
		return employerService.getAllJobAdvertisementSorted();
	}
	
	@GetMapping("/getAllEmployerJobAdvertisements")
	public DataResult<Employer> getAllEmployerJobAdvertisements(@RequestParam int id){
		return employerService.getAllEmployerJobAdvertisements(id);
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
