package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.EmployerService;
import com.kodlamaio.hrms.core.mailvalidate.MailValid;
import com.kodlamaio.hrms.core.mailvalidate.MailValidService;
import com.kodlamaio.hrms.core.mailvalidation.MailValidationService;
import com.kodlamaio.hrms.core.register.Register;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.MemberDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.Member;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;


@Service
public class EmployerManager implements EmployerService{

	EmployerDao employerDao;
	MailValidService mailValidateService;
	MailValidationService mailValidationService;
	@Autowired
	public EmployerManager(EmployerDao employerDao,MailValidService mailValidateService,MailValidationService mailValidationService) {
		this.employerDao=employerDao;
		this.mailValidateService=mailValidateService;
		this.mailValidationService=mailValidationService;
	}

	
	@Override
	public SuccessDataResult<List<Employer>>  getAll() {
		return new SuccessDataResult<List<Employer>>(employerDao.findAll(), "Data Listelendi");
	}
	
	@Override
	public Result add(Employer employer) {
		
		boolean companyName = true;
		boolean webSite = true;
		boolean eMail = true;
		boolean password = true;
		boolean areaCode = true;
		boolean phoneNumber = true;
		boolean mailUsed = true;
	
		String error="";
		
		employer = Register.normalizeEmployer(employer);
		
		if(employer.getMember().getPassword().isEmpty()) {
			error+=" Şifre boş olamaz";
         	}
		else if(!employer.getMember().getPassword().equals(employer.getMember().getPasswordRepeat())) {
			error+=" Şifreler uyuşmuyor";
			password = false;
		}
		
		String[] endMail;
		
		if(employer.getCompanyName().isEmpty()) {
			error+=" Şirket adı boş olamaz";
			companyName = false;
		}
		
		if(employer.getWebsite().isEmpty()) {
			error+=" Web sitesi adresi boş olamaz";
		}
		
		for (PhoneNumber number : employer.getPhoneNumbers()) {
			
			if(number.getAreaCode().isEmpty()) {
				error+="Alan kodu boş olamaz";
				areaCode=false;
			}
			if(number.getPhoneNumber().length()!=10) {
				error+="Telefon numarası 10 karakter olmalıdır";
				phoneNumber=false;
			}
			
		}
		
		
		if(mailValidateService.mailIsValid(employer.getMember().getEMail())) {
		if(!employer.getMember().getEMail().isEmpty()) {
			endMail = employer.getMember().getEMail().split("@");
			
		
		if(employer.getWebsite().equals(endMail[1])&&endMail.length==2) {
			
		}else {
			eMail=false;
			error+=" Mail adresi web sitesi ile aynı domaine sahip olmalıdır";
		}
		
		}else {
			eMail=false;
			error+=" Mail adresi boş olamaz";
		}
		
		}else {
			eMail=false;
			error+=" Geçersiz e-mail adresi";
		}
		
		
	    if(employerDao.findByMember_eMail(employer.getMember().getEMail())==null) {
			mailUsed = false;
		}else {
			error+=" E-Mail adresi zaten kullanılıyor";
			return new ErrorResult(error);
		}    
		
		if(companyName&&webSite&&eMail&&password&&areaCode&&phoneNumber&&!mailUsed) {
			employerDao.save(employer);	
			mailValidationService.sendMailValidation(employer.getMember().getEMail());
			return new SuccessResult("İş veren kayıt edildi");
		}else {
			return new ErrorResult(error);
		}
		


	}
	
	

}
