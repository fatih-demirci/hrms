package com.kodlamaio.hrms.business.conretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrms.dataAccess.abstracts.PhoneNumberDao;
import com.kodlamaio.hrms.dataAccess.abstracts.TypeOfWorkDao;
import com.kodlamaio.hrms.dataAccess.abstracts.WebsiteDao;
import com.kodlamaio.hrms.dataAccess.abstracts.WorkingTimeDao;
import com.kodlamaio.hrms.entities.conretes.EMailVerification;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.JobPosition;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;
import com.kodlamaio.hrms.entities.conretes.StaffConfirmation;
import com.kodlamaio.hrms.entities.conretes.Website;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;

@Service
public class EmployerManager implements EmployerService{

	EmployerDao employerDao;
	MailValidService mailValidService;
	MailValidationService mailValidationService;
	PhoneNumberDao phoneNumberDao;
	JobPositionDao jobPositionDao;
	CityDao cityDao;
	TypeOfWorkDao typeOfWorkDao;
	WorkingTimeDao workingTimeDao;
	JobAdvertisementDao jobAdvertisementDao;
	WebsiteDao websiteDao;
	@Autowired
	public EmployerManager(EmployerDao employerDao,
			MailValidService mailValidService,
			MailValidationService mailValidationService,
			PhoneNumberDao phoneNumberDao,
			JobPositionDao jobPositionDao,
			CityDao cityDao,
			TypeOfWorkDao typeOfWorkDao,
			WorkingTimeDao workingTimeDao,
			JobAdvertisementDao jobAdvertisementDao,
			WebsiteDao websiteDao) {
		
		this.employerDao=employerDao;
		this.mailValidService=mailValidService;
		this.mailValidationService=mailValidationService;
		this.phoneNumberDao=phoneNumberDao;
		this.jobPositionDao=jobPositionDao;
		this.cityDao=cityDao;
		this.typeOfWorkDao =  typeOfWorkDao;
		this.workingTimeDao=workingTimeDao;
		this.jobAdvertisementDao=jobAdvertisementDao;
		this.websiteDao=websiteDao;
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
					if(j>=0) {
				endWebsite += website.getWebsite().charAt(j);
					}
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
			
			
			if(employer.getJobAdvertisements()!=null) {
			
			List<JobAdvertisement> jobA= new ArrayList<JobAdvertisement>();
			for (JobAdvertisement jobAdvertisement : employer.getJobAdvertisements()) {
			 	
				if(typeOfWorkDao.findById(jobAdvertisement.getTypeOfWork().getId())!=null) {
					jobAdvertisement.setTypeOfWork(typeOfWorkDao.findById(jobAdvertisement.getTypeOfWork().getId()));  
				}else {
					jobAdvertisement.setTypeOfWork(null);
				}
				
				if(workingTimeDao.findById(jobAdvertisement.getWorkingTime().getId())!=null) {
					jobAdvertisement.setWorkingTime(workingTimeDao.findById(jobAdvertisement.getWorkingTime().getId()));
				}else {
					jobAdvertisement.setWorkingTime(null);
				}
				
				jobAdvertisement.setApproved(false);
							
				jobAdvertisement.setReleaseDate(new Date());

			   	jobAdvertisement.setEmployer(employer);	  
			   	
				JobPosition jobP =jobPositionDao.findById(jobAdvertisement.getJobPosition().getId());
						
				
				jobAdvertisement.setJobPosition(jobP);
				
				
				jobAdvertisement.setCity(cityDao.findById(jobAdvertisement.getCity().getId()));
				
				
				jobA.add(jobAdvertisement);
				
				jobAdvertisement.getCity().setJobAdvertisements(jobA);
				
				jobP.setJobAdvertisements(jobA);

			}	
			employer.setJobAdvertisements(jobA);
			
			}

			
			
			employer.setStaffConfirmation(new StaffConfirmation());
			employer.setEMailVerification(new EMailVerification());
			
			employer.getEMailVerification().setMailVerified(false);
			employer.getStaffConfirmation().setStaffApproved(false);
			
			
			
			for(Website w: employer.getWebsites()) {
				w.setEmployer(employer);
			}
			
			
			
			employerDao.save(employer);	
			
			mailValidationService.sendMailValidation(employer.getMember().getEMail());
			return new SuccessResult("İş veren kayıt edildi");
		}else {
			return new ErrorResult(error);
		}
		
		

	}

	public Result updatePassword(int employerId,String password,String passwordRepeat) {
		if(password==null||passwordRepeat==null) {
			return new ErrorResult("Şifre ve şifre tekrar alanları zorunlu");
		}else if(password.isEmpty()||passwordRepeat.isEmpty()) {
			return new ErrorResult("Şifre ve şifre tekrar alanları zorunlu");
		}
		if(password.equals(passwordRepeat)) {
			Employer employer = employerDao.findById(employerId);
			if(employer==null) {
				return new ErrorResult("İş veren bulunamadı");
			}
			employer.getMember().setPassword(password);
			employer.getMember().setPasswordRepeat(passwordRepeat);
			employerDao.save(employer);
			return new SuccessResult("Şifre değiştirildi.");
		}else {
			return new ErrorResult("Şifre ve şifre tekrar aynı değil");
		}
	}
	
	public Result updateWebsite(int employerId , Website website) {
		Employer employer = employerDao.findById(employerId);
		if(employer==null) {
			return new ErrorResult("İşveren bulunamadı");
		}

		if(websiteDao.findByWebsite(website.getWebsite())!=null) {
			return new ErrorResult("Website adresi zaten kayıtlı");
		}
		
		String[] endMail=employer.getMember().getEMail().split("@");
		String endWebsite="";
		
		for(int i=endMail[1].length();i>0 ;i--) {
			int j =website.getWebsite().length()-i;
			if(j>=0) {
		endWebsite += website.getWebsite().charAt(j);
			}
		}	
		
		if(endWebsite.equals(employer.getMember().getEMail().split("@")[1])) {
			
			Website exWebsite = websiteDao.findById(website.getExWebsiteId());
			
			if(exWebsite==null) {
				return new ErrorResult("Güncellenecek website adresi bulunamadı");
			}
			
			for(Website web : employer.getWebsites()) {
				if(web.getExWebsiteId()==website.getExWebsiteId()) {
					web.setWebsite(website.getWebsite());
					employerDao.save(employer);
					return new SuccessResult("Website adresi güncelleme isteği personel onayı için tekrar gönderildi");
				}
			}
			
			website.setEmployer(employer);
			employer.getWebsites().add(website);
			
			employerDao.save(employer);
			
			return new SuccessResult("Website adresi güncelleme isteği personel onayı için gönderildi");
		}else {
			return new ErrorResult("Website adresi e-mail adresiyle aynı domaine sahip olmalıdır");
		}
		
	}
	
	public Result updatePhoneNumber(int employerId, PhoneNumber phoneNumber) {
	Employer employer = employerDao.findById(employerId);
		
		if(employer==null) {
			return new ErrorResult("İş veren bulunamadı");
		}
		if(phoneNumber.getPhoneNumber()==null) {
			return new ErrorResult("Telefon numarası boş olamaz");
		}else if(phoneNumber.getPhoneNumber().isEmpty()) {
			return new ErrorResult("Telefon numarası boş olamaz");
		}
		if(phoneNumber.getPhoneNumber().length()!=10) {
			return new ErrorResult("Telefon numarası 10 hane olmalıdır");
		}
		if(phoneNumber.getAreaCode()==null) {
			return new ErrorResult("Alan kodu boş olamaz");
		}else if(phoneNumber.getAreaCode().isEmpty()) {
			return new ErrorResult("Alan kodu boş olamaz");
		}
		if(phoneNumber.getExPhoneNumberId()==0) {
			return new ErrorResult("Telefon numarası güncelleme değil");
		}
		
		PhoneNumber exPhoneNumber = phoneNumberDao.findById(phoneNumber.getExPhoneNumberId());
		if(exPhoneNumber==null) {
			return new ErrorResult("Güncellenecek telefon numarası bulunamadı");
		}
		
		if(phoneNumberDao.findByPhoneNumber(phoneNumber.getPhoneNumber())!=null) {
			return new ErrorResult("Telefon numarası zaten kayıtlı");
		}
		
		
		for(PhoneNumber number: employer.getPhoneNumbers()) {
			if(number.getExPhoneNumberId()==phoneNumber.getExPhoneNumberId()) {
				number.setPhoneNumber(phoneNumber.getPhoneNumber());
				employerDao.save(employer);
				return new SuccessResult("Telefon numarası güncelleme isteği personel onayına tekrar gönderildi.");
			}
		}
		phoneNumber.setEmployer(employer);
		employer.getPhoneNumbers().add(phoneNumber);
		employerDao.save(employer);
		return new SuccessResult("Telefon numarası güncelleme isteği personel onayına gönderildi.");
	}
	
	@Override
	public DataResult<Employer> getAllEmployerJobAdvertisements(int id){
		Employer employer = employerDao.findById(id);
		
		if(employer!=null) {

			return new SuccessDataResult<Employer>(employer,"İş veren getirildi");
			
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
	
	@Override
	public DataResult<List<EmployerWithAdvertisementDto>>  getEmployerWithAdvertisementDetails(boolean isOpen, boolean approved,int pageNo, int pageSize){
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
	 return new SuccessDataResult<List<EmployerWithAdvertisementDto>>(employerDao.getEmployerWithAdvertisementDetails(isOpen,approved,pageable), "İlanlar listelendi");
	}
	
	public DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(boolean isOpen, boolean approved,int pageNo, int pageSize, String city, String typeOfWork){
		Pageable pageable = PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<EmployerWithAdvertisementDto>>(employerDao.getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(isOpen, approved, pageable, city,typeOfWork),"İlanlar şehire göre listelendi");
	}
																														

}


