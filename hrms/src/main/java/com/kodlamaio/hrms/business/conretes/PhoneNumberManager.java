package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.PhoneNumberService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.PhoneNumberDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;

@Service
public class PhoneNumberManager implements PhoneNumberService{

	PhoneNumberDao phoneNumberDao;
	EmployerDao employerDao;
	
	@Autowired
	public PhoneNumberManager(PhoneNumberDao phoneNumberDao,EmployerDao employerDao) {
		this.phoneNumberDao=phoneNumberDao;
		this.employerDao=employerDao;
	}
	
	@Override
	public DataResult<PhoneNumber> isUpdate(int employerId,int exPhoneNumberId) {
		return new SuccessDataResult<PhoneNumber>(phoneNumberDao.findByEmployer_IdAndExPhoneNumberId(employerId,exPhoneNumberId));
	}
	
	@Override
	public Result approve(int employerId, int phoneNumberId) {
		Employer employer = employerDao.findById(employerId);
		if(employer==null) {
			return new ErrorResult("İşveren bulunamadı");
		}
		PhoneNumber phoneNumber = phoneNumberDao.findById(phoneNumberId);
		if(phoneNumber==null) {
			return new ErrorResult("Onaylanacak telefon numarası bulunamadı");
		}

		PhoneNumber updatePhoneNumber = phoneNumberDao.findByEmployer_IdAndExPhoneNumberId(employerId, phoneNumberId);
		
		if(updatePhoneNumber==null) {
			return new ErrorResult("Telefon numarasının güncellemesi yok");
		}
		
		phoneNumberDao.deleteById(updatePhoneNumber.getId());
		
		for(PhoneNumber number : employer.getPhoneNumbers()) {
			if(number.getId()==phoneNumberId) {
				number.setPhoneNumber(updatePhoneNumber.getPhoneNumber());
				number.setAreaCode(updatePhoneNumber.getAreaCode());
			}
		}
		
		employerDao.save(employer);
		return new SuccessResult("Telefon numarası güncellemesi onaylandı");
	}
	
	@Override
	public Result addEmployerPhone(int id,Employer employer,PhoneNumber phoneNumber) {
			
		
		return new SuccessResult("Telefon numarası eklendi");
	}

}
