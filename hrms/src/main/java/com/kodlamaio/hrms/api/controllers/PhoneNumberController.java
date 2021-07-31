package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.PhoneNumberService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

@RestController
@RequestMapping("/PhoneNumber")
@CrossOrigin
public class PhoneNumberController {
	PhoneNumberService phoneNumberService;
	
	@Autowired
	public PhoneNumberController(PhoneNumberService phoneNumberService) {
		this.phoneNumberService=phoneNumberService;
	}
	
	@GetMapping("/isUpdate")
	public DataResult<PhoneNumber> isUpdate(int employerId,int exPhoneNumberId){
		return phoneNumberService.isUpdate(employerId, exPhoneNumberId);
	}
	
	@PostMapping("/approve")
	public Result approve(int employerId, int phoneNumberId) {
		return phoneNumberService.approve(employerId, phoneNumberId);
	}
}
