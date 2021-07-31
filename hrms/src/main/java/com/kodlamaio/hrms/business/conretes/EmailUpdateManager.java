package com.kodlamaio.hrms.business.conretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.EmailUpdateService;
import com.kodlamaio.hrms.core.mailvalidate.MailValidService;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.entities.conretes.EmailUpdate;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.Website;

@Service
public class EmailUpdateManager implements EmailUpdateService {
	EmployerDao employerDao;
	MailValidService mailValidService;
	public EmailUpdateManager(EmployerDao employerDao,MailValidService mailValidService) {
		this.employerDao=employerDao;
		this.mailValidService=mailValidService;
	}
	
	@Override
	public Result update(int employerId, EmailUpdate emailUpdate) {
		if(emailUpdate.getEmailUpdate()==null) {
			return new ErrorResult("e-mail adresi boş olamaz");
		}else {
			if(mailValidService.mailIsValid(emailUpdate.getEmailUpdate())) {
				Employer employer = employerDao.findById(employerId);
				if(employer!=null ){
							
						
						String[] endMail = emailUpdate.getEmailUpdate().split("@");
						
						String endWebsite="";
							
						for(Website website : employer.getWebsites()) {	
							
							for(int i=endMail[1].length();i>0 ;i--) {
								int j =website.getWebsite().length()-i;
								if(j>=0) {
							endWebsite += website.getWebsite().charAt(j);
								}
							}
							break;
						}		
							
						
					if(!(endWebsite.equals(endMail[1])&&endMail.length==2)) {
						return new ErrorResult("E-mail adresi domaini, websitesi ile aynı olmalıdır");
					}else {
						if(employer.getEmailUpdate()==null) {
							employer.setEmailUpdate(new EmailUpdate());
						}
						
						employer.getEmailUpdate().setEmailUpdate(emailUpdate.getEmailUpdate());
						employerDao.save(employer);
						return new SuccessResult("Güncelleme isteği gönderildi");
					}
					
				}else {
					return new ErrorResult("İş veren bulunamadı");
				}
			}else {
				return new ErrorResult("Geçersiz e-mail adresi");
			}
			
		}
		
		
		
	}
	

	@Override
	public Result approve(int employerId) {
		Employer employer = employerDao.findById(employerId);
		if(employer==null) {
			return new ErrorResult("İşveren bulunamadı");
			}
		if(employer.getEmailUpdate()==null) {
			return new ErrorResult("İşverenin e-mail güncellemesi bulunamadı");
		}
		employer.getMember().setEMail(employer.getEmailUpdate().getEmailUpdate());
		employer.setEmailUpdate(null);
		employerDao.save(employer);
		return new SuccessResult("E-mail adresi güncellemesi onaylandı");
		}
}
