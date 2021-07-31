package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.WebsiteService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.WebsiteDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;
import com.kodlamaio.hrms.entities.conretes.Website;

@Service
public class WebsiteManager implements WebsiteService{
	WebsiteDao websiteDao;
	EmployerDao employerDao;
	
	@Autowired
	public WebsiteManager(WebsiteDao websiteDao,EmployerDao employerDao) {
		this.websiteDao=websiteDao;
		this.employerDao=employerDao;
	}
	
	@Override
	public DataResult<Website> isUpdate(int id) {
		return new SuccessDataResult<Website>(websiteDao.findByExWebsiteId(id)) ;
	}
	
	@Override
	public Result approve(int employerId,int websiteId) {
		Employer employer = employerDao.findById(employerId);
		if(employer==null) {
			return new ErrorResult("İşveren bulunamadı");
		}
		Website website = websiteDao.findById(websiteId);
		if(website==null) {
			return new ErrorResult("Onaylanacak website adresi bulunamadı");
		}

		Website updateWebsite = websiteDao.findByExWebsiteId(websiteId);
		
		if(updateWebsite==null) {
			return new ErrorResult("Website adresinin güncellemesi yok");
		}
		
		websiteDao.deleteById(updateWebsite.getId());
		
		for(Website site : employer.getWebsites()) {
			if(site.getId()==websiteId) {
				site.setWebsite(updateWebsite.getWebsite());
			}
		}
		
		employerDao.save(employer);
		return new SuccessResult("Website adresi güncellemesi onaylandı");
	}

}
