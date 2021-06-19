package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.kodlamaio.hrms.business.abstracts.PhoneNumberService;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.PhoneNumberDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

public class PhoneNumberManager implements PhoneNumberService{

	PhoneNumberDao phoneNumberDao;
	
	@Autowired
	public PhoneNumberManager(PhoneNumberDao phoneNumberDao) {
		this.phoneNumberDao=phoneNumberDao;
	}
	
	@Override
	public Result addEmployerPhone(int id,Employer employer,PhoneNumber phoneNumber) {
			
		
		return new SuccessResult("Telefon numarası eklendi");
	}

}
