package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.CompanyNameUpdateService;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;

import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.entities.conretes.CompanyNameUpdate;
import com.kodlamaio.hrms.entities.conretes.Employer;

@Service
public class CompanyNameUpdateManager implements CompanyNameUpdateService{
	EmployerDao employerDao;
	
	@Autowired
	public CompanyNameUpdateManager(EmployerDao employerDao) {
		this.employerDao=employerDao;
	}
	
	@Override
	public Result update(int employerId ,CompanyNameUpdate companyNameUpdate) {
		if(companyNameUpdate.getCompanyNameUpdate()==null) {
			return new ErrorResult("İş yeri adı boş olamaz");
		}
		Employer employer = employerDao.findById(employerId);
		if(employer==null) {
			return new ErrorResult("Şirket bulunamadı");
		}
	
		if(employer.getCompanyNameUpdate()==null) {
			employer.setCompanyNameUpdate(new CompanyNameUpdate());
		}
			employer.getCompanyNameUpdate().setCompanyNameUpdate(companyNameUpdate.getCompanyNameUpdate());
		
			employerDao.save(employer);
		
		return new SuccessResult("Güncelleme isteği gönderildi");
	}
	
	public Result approve(int employerId) {
		Employer employer = employerDao.findById(employerId);
		employer.setCompanyName(employer.getCompanyNameUpdate().getCompanyNameUpdate());
		employer.setCompanyNameUpdate(null);
		employerDao.save(employer);
		return new SuccessResult("İşyeri adı güncellemesi onaylandı");
	}

}
