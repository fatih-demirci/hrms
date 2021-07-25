package com.kodlamaio.hrms.core.mernis;

public interface PersonValidationService {
	public boolean validate(Long identificationNumber, String name, String lastName, int yearOfBirth);
}
