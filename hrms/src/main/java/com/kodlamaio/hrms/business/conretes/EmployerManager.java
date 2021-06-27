package com.kodlamaio.hrms.business.conretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.EmployerService;
import com.kodlamaio.hrms.core.mailvalidate.MailValidService;
import com.kodlamaio.hrms.core.mailvalidation.MailValidationService;
import com.kodlamaio.hrms.core.register.Register;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrms.dataAccess.abstracts.PhoneNumberDao;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.JobPosition;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;
import com.kodlamaio.hrms.entities.conretes.Website;


@Service
public class EmployerManager implements EmployerService{

	EmployerDao employerDao;
	MailValidService mailValidService;
	MailValidationService mailValidationService;
	PhoneNumberDao phoneNumberDao;
	JobPositionDao jobPositionDao;
	CityDao cityDao;
	@Autowired
	public EmployerManager(EmployerDao employerDao,
			MailValidService mailValidService,
			MailValidationService mailValidationService,
			PhoneNumberDao phoneNumberDao,
			JobPositionDao jobPositionDao,
			CityDao cityDao) {
		
		this.employerDao=employerDao;
		this.mailValidService=mailValidService;
		this.mailValidationService=mailValidationService;
		this.phoneNumberDao=phoneNumberDao;
		this.jobPositionDao=jobPositionDao;
		this.cityDao=cityDao;
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
		boolean phoneNumberUsed = false;
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
		
		for(Website website : employer.getWebsites()) {
			if(website.getWebsite().isEmpty()) {
				webSite=false;
				error+=" Web sitesi adresi boş olamaz";
			}
			if(website.getWebsite().length()<3) {
				webSite=false;
				error+=" Geçersiz Web sitesi adresi";
			}
		}
		
		
		
		for (PhoneNumber number : employer.getPhoneNumbers()) {
			
			if(number.getAreaCode().isEmpty()) {
				error+=" Alan kodu boş olamaz";
				areaCode=false;
			}
			if(number.getPhoneNumber().length()!=10) {
				error+=" Telefon numarası 10 karakter olmalıdır";
				phoneNumber=false;
			}
			
			if(phoneNumberDao.findByPhoneNumber(number.getPhoneNumber())!=null) {
				phoneNumberUsed=true;
				error+=" Telefon numarası zaten kullanılıyor " +number.getPhoneNumber();
			}		
		}
		
		
		
		if(mailValidService.mailIsValid(employer.getMember().getEMail())) {
	
			
			if(!employer.getMember().getEMail().isEmpty()) {			
				
			endMail = employer.getMember().getEMail().split("@");
			
			String endWebsite="";
			if(webSite) {
				
			for(Website website : employer.getWebsites()) {
				
				
				for(int i=endMail[1].length();i>0 ;i--) {
					int j =website.getWebsite().length()-i;
				endWebsite += website.getWebsite().charAt(j);
				}	
			}		
				
			
		if(endWebsite.equals(endMail[1])&&endMail.length==2) {
				
			
		}else {
			webSite=false;
			error+=" Mail adresi hatalı. Web sitesi ile aynı domaine sahip olmalıdır";		

		}
			}	
		
		
			}}else {
			eMail=false;
			error+=" Geçersiz e-mail adresi";
		}
					
			
		
		
		
		
		
	    if(employerDao.findByMember_eMail(employer.getMember().getEMail())==null) {
			mailUsed = false;
		}else {
			error+=" E-Mail adresi zaten kullanılıyor";
			return new ErrorResult(error);
		}    
		
		if(companyName&&webSite&&eMail&&password&&areaCode&&phoneNumber&&!mailUsed&&!phoneNumberUsed) {
			
			
			for (PhoneNumber number : employer.getPhoneNumbers()) {	
				number.setEmployer(employer);
			}
			
			
			
			List<JobAdvertisement> jobA= new ArrayList<JobAdvertisement>();
			for (JobAdvertisement jobAdvertisement : employer.getJobAdvertisements()) {
			 	
				jobAdvertisement.setReleaseDate(new Date());
				
			   	jobAdvertisement.setEmployer(employer);	  
			   	
				JobPosition jobP =jobPositionDao.findById(jobAdvertisement.getJobPosition().getId());
				jobP.setJobAdvertisements(jobA);
				
				
				jobAdvertisement.setJobPosition(jobP);
				
				
				jobAdvertisement.setCity(cityDao.findById(jobAdvertisement.getCity().getId()));
				jobAdvertisement.getCity().setJobAdvertisements(jobA);
				
				jobA.add(jobAdvertisement);

			}	
			
			

			employer.setJobAdvertisements(jobA);
			
			
			employerDao.save(employer);	
			
			mailValidationService.sendMailValidation(employer.getMember().getEMail());
			return new SuccessResult("İş veren kayıt edildi");
		}else {
			return new ErrorResult(error);
		}
		
		

	}
	
	@Override
	public DataResult<Employer> getAllEmployersJobAdvertisements(int id){
		Employer employer = employerDao.findById(id);
		
		if(employer!=null) {
		if(employer.getJobAdvertisements().size()>0) {
			Employer tempEmployer = new Employer();
			tempEmployer.setCompanyName(employer.getCompanyName());
			
			List<JobAdvertisement> jobA = new ArrayList<JobAdvertisement>();
			for (JobAdvertisement jobAdvertisement : employer.getJobAdvertisements()) {
				if(jobAdvertisement.isJobAdvertisementOpen()) {
				JobAdvertisement tempJobAdvertisement = new JobAdvertisement();
				
				tempJobAdvertisement.setJobPosition(jobAdvertisement.getJobPosition());
				tempJobAdvertisement.setOpenPositions(jobAdvertisement.getOpenPositions());
				tempJobAdvertisement.setReleaseDate(jobAdvertisement.getReleaseDate());
				tempJobAdvertisement.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
				
				jobA.add(tempJobAdvertisement);
				}
			}
			if(jobA.size()>0) {
			tempEmployer.setJobAdvertisements(jobA);
			
			return new SuccessDataResult<Employer>(tempEmployer,"İş verenin iş ilanları listelendi");
			}else {
				return new ErrorDataResult("İş verenin hiç aktif iş ilanı yok");
			}
		}
		return new ErrorDataResult("İş verenin hiç iş ilanı yok");
	}else {
		return new ErrorDataResult("İş veren bulunamadı");
	}
		
	}
	
	@Override
	public SuccessDataResult<List<Employer>> getAllJobAdvertisements(List<Employer> employers) {
		
		List<Employer> returnEmployers = new ArrayList<Employer>();
		
		if(employers==null) {
			employers = new ArrayList<Employer>();
			
			employers = employerDao.findAll();
		}
		
		for (Employer employer : employers) {
			
			if(employer.getJobAdvertisements().size()>0) {
			Employer tempEmployer = new Employer();
			tempEmployer.setCompanyName(employer.getCompanyName());
			
			List<JobAdvertisement> jobA = new ArrayList<JobAdvertisement>();
			for (JobAdvertisement jobAdvertisement : employer.getJobAdvertisements()) {
				JobAdvertisement tempJobAdvertisement = new JobAdvertisement();
				
				tempJobAdvertisement.setJobPosition(jobAdvertisement.getJobPosition());
				tempJobAdvertisement.setOpenPositions(jobAdvertisement.getOpenPositions());
				tempJobAdvertisement.setReleaseDate(jobAdvertisement.getReleaseDate());
				tempJobAdvertisement.setApplicationDeadline(jobAdvertisement.getApplicationDeadline());
				
				jobA.add(tempJobAdvertisement);
			}
			tempEmployer.setJobAdvertisements(jobA);
			returnEmployers.add(tempEmployer);
			}
			
		}
		return new SuccessDataResult<List<Employer>>(returnEmployers,"İş ilanları listelendi");
	}


	@Override
	public DataResult <List<Employer>> getAllJobAdvertisementSorted() {

		Sort sort = Sort.by(Sort.Direction.ASC,"jobAdvertisements_ApplicationDeadline");
		List<Employer> employers = employerDao.findAll(sort);
		return new SuccessDataResult<List<Employer>>(getAllJobAdvertisements(employers).getData(),"İş ilanları sıralı listelendi");
		
	}
	

	
}
