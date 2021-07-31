package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

public interface PhoneNumberService {
	Result addEmployerPhone(int id, Employer employer ,PhoneNumber phoneNumber);
	public DataResult<PhoneNumber> isUpdate(int employerId,int exPhoneNumberId);
	public Result approve(int employerId, int phoneNumberId);
}
