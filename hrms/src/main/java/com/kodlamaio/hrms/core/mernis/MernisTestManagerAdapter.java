package com.kodlamaio.hrms.core.mernis;

import org.springframework.stereotype.Component;

@Component
public class MernisTestManagerAdapter implements PersonValidationService{

	@Override
	public boolean validate(Long identificationNumber, String name, String lastName, int yearOfBirth) {
		return true;
	}
	
	

}
