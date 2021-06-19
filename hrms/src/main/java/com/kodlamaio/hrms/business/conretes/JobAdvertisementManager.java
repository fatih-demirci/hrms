package com.kodlamaio.hrms.business.conretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.JobPosition;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	JobAdvertisementDao jobAdvertisementDao;
	EmployerDao employerDao;
	CityDao cityDao;
	JobPositionDao jobPositionDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
			EmployerDao employerDao,
			CityDao cityDao,
			JobPositionDao jobPositionDao) {
		this.jobAdvertisementDao=jobAdvertisementDao;
		this.employerDao=employerDao;
		this.cityDao=cityDao;
		this.jobPositionDao=jobPositionDao;
	}
	
	@Override
	public Result jobAdvertisementOpen(int jobAdvertisementId,boolean isJobAdvertisementOpen) {
		
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.getById(jobAdvertisementId);
		
		if(jobAdvertisement != null) {
			
			if(!jobAdvertisement.isJobAdvertisementOpen()&&isJobAdvertisementOpen==true) {
			jobAdvertisement.setJobAdvertisementOpen(isJobAdvertisementOpen);	
			jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("İş ilanı aktive edildi");
			}else if(jobAdvertisement.isJobAdvertisementOpen()&&isJobAdvertisementOpen==true) {
				return new SuccessResult("İş ilanı zaten aktif");
			}else if(jobAdvertisement.isJobAdvertisementOpen()&&isJobAdvertisementOpen==false) {
				jobAdvertisement.setJobAdvertisementOpen(isJobAdvertisementOpen);
				jobAdvertisementDao.save(jobAdvertisement);
				return new SuccessResult("İş ilanı pasif yapıldı");
			}else {
				return new SuccessResult("İş ilanı zaten pasif");
			}
			
			
			
		}else {
			return new ErrorResult("İş ilanı bulunamadı");
		}
	}

	@Override
	public Result addAdvertisement(JobAdvertisement jobAdvertisement, int employerId) {
		// release date otomatik atanacak
		String error="";
		boolean isJobDescriptionEmpty = true;
		boolean isMinSalaryInvalid = false;
		boolean isMaxSalaryInvalid = false;
		boolean isMinSalaryZero = false;
		boolean isMaxSalaryZero = false;
		boolean isSalaryInvalid = false;
		boolean isOpenPosisitonsEmpty = true;
		boolean isApplicationDeadlineInvalid = false;
		boolean isReleaseDateEmpty = true;
		boolean isJobPositionInvalid = false;
		boolean isCityInvalid = false;
		
		if(!jobAdvertisement.getJobDescription().isEmpty()) {
			isJobDescriptionEmpty = false;
		}else {
			error+="İş açıklaması boş olamaz.";
		}
		if(jobAdvertisement.getMinSalary()==0) {
			isMinSalaryZero=true;
			error+=" Taban maaş 0 olamaz.";
		}
		if(jobAdvertisement.getMaxSalary()==0) {
			isMaxSalaryZero=true;
			error+=" Tavan maaş 0 olamaz.";
		}
		if(jobAdvertisement.getMinSalary()<0) {
			error+=" Geçersiz taban maaş.";
			isMinSalaryInvalid=true;
		}
		if(jobAdvertisement.getMaxSalary()<0) {
			error+=" Geçersiz tavan maaş.";
			isMaxSalaryInvalid=true;
		}
		if(!isMinSalaryInvalid&&!isMaxSalaryInvalid) {
			if(jobAdvertisement.getMinSalary()>jobAdvertisement.getMaxSalary()) {
				isSalaryInvalid=true;
				error+=" Tavan maaş taban maaştan düşük olamaz.";
			}
		}
		
		if(jobAdvertisement.getJobPosition().getId()<0) {
			error+=" Açık pozisyon girişi hatalı";
			isJobPositionInvalid = true;
		}else {
		
		if(jobAdvertisement.getOpenPositions()>0) {
			isOpenPosisitonsEmpty = false;
		}else {
			error+="  Açık pozisyon sayısı boş olamaz.";
		}
		}
		
		if(jobAdvertisement.getReleaseDate()!=null) {
			isReleaseDateEmpty=false;
			if(jobAdvertisement.getApplicationDeadline()!=null) {
			
				if(jobAdvertisement.getApplicationDeadline().compareTo(jobAdvertisement.getReleaseDate())<=0) {
					error+=" Son başvuru tarihi ileri bir tarih olmalıdır.";
					isApplicationDeadlineInvalid = true;
				}
			}else {
				error+=" Son başvuru tarihi boş olamaz.";
			}		
		}else {
			error+=" Yayınlanma tarihi boş olamaz.";
		}

		if(jobAdvertisement.getCity().getId()<0) {
			error+=" Şehir girişi hatalı";
			isCityInvalid=true;
		}
		
		System.out.println("isJobDescriptionEmpty " + !isJobDescriptionEmpty   +" isMinSalaryInvalid " +
				 !isMinSalaryInvalid  + " isMaxSalaryInvalid " + !isMaxSalaryInvalid + " isSalaryInvalid " + !isSalaryInvalid + " isOpenPosisitonsEmpty "+!isOpenPosisitonsEmpty+
				" isApplicationDeadlineInvalid "+!isApplicationDeadlineInvalid+" isReleaseDateEmpty "+!isReleaseDateEmpty+" isJobPositionInvalid "+!isJobPositionInvalid+
				" isCityInvalid " +!isCityInvalid+ " isMinSalaryZero " + !isMinSalaryZero + " isMaxSalaryZero " + !isMaxSalaryZero);
		
		if(!isJobDescriptionEmpty&&!isMinSalaryInvalid&&!isMaxSalaryInvalid&&!isSalaryInvalid&&!isOpenPosisitonsEmpty&&
				!isApplicationDeadlineInvalid&&!isReleaseDateEmpty&&!isJobPositionInvalid&&!isCityInvalid&&!isMinSalaryZero&&!isMaxSalaryZero){
	
			if(jobPositionDao.findById(jobAdvertisement.getJobPosition().getId())!=null) {
				if(cityDao.findById(jobAdvertisement.getCity().getId())!=null) {
					Employer employer = employerDao.findById(employerId);
					if(employer!=null) {
				List<JobAdvertisement> jobAdvertisements = employer.getJobAdvertisements();
				jobAdvertisement.setCity(cityDao.findById(jobAdvertisement.getCity().getId()));
				jobAdvertisement.setEmployer(employer);
				jobAdvertisement.setJobPosition(jobPositionDao.findById(jobAdvertisement.getJobPosition().getId()));
				
				jobAdvertisements.add(jobAdvertisement);

				employer.setJobAdvertisements(jobAdvertisements);
				employerDao.save(employer);
				return new SuccessResult("İş ilanı kayıt edildi.");								
					}else {
						error+=" İş veren bulunamadı.";
					}
								
				}else {
					error+=" Şehir bulunamadı";
				}		
				
			}else {
				error+=" İş pozisyonu bulunamadı";
			}						
			
		}
		
		return new ErrorResult(error);	

	}

}
