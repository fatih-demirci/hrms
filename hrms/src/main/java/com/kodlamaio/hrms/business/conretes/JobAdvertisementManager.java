package com.kodlamaio.hrms.business.conretes;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrms.dataAccess.abstracts.TypeOfWorkDao;
import com.kodlamaio.hrms.dataAccess.abstracts.WorkingTimeDao;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.WorkingTime;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;

import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	JobAdvertisementDao jobAdvertisementDao;
	EmployerDao employerDao;
	CityDao cityDao;
	JobPositionDao jobPositionDao;
	TypeOfWorkDao typeOfWorkDao;
	WorkingTimeDao workingTimeDao;
	
	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
			EmployerDao employerDao,
			CityDao cityDao,
			JobPositionDao jobPositionDao,
			TypeOfWorkDao typeOfWorkDao,
			WorkingTimeDao workingTimeDao) {
		this.jobAdvertisementDao=jobAdvertisementDao;
		this.employerDao=employerDao;
		this.cityDao=cityDao;
		this.jobPositionDao=jobPositionDao;
		this.typeOfWorkDao=typeOfWorkDao;
		this.workingTimeDao = workingTimeDao;
	}
	
	@Override
	public Result jobAdvertisementOpen(int jobAdvertisementId,boolean isJobAdvertisementOpen) {
		
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId);
		
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
	public Result jobAdvertisementApprove(int jobAdvertisementId,boolean isJobAdvertisementApprove) {
		
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId);
		
		if(jobAdvertisement != null) {
			
			if(!jobAdvertisement.isApproved()&&isJobAdvertisementApprove==true) {
			jobAdvertisement.setApproved(isJobAdvertisementApprove);	
			jobAdvertisementDao.save(jobAdvertisement);
			return new SuccessResult("İş ilanı onaylandı edildi");
			}else if(jobAdvertisement.isApproved()&&isJobAdvertisementApprove==true) {
				return new SuccessResult("İş ilanı zaten onaylı");
			}else if(jobAdvertisement.isApproved()&&isJobAdvertisementApprove==false) {
				jobAdvertisement.setApproved(isJobAdvertisementApprove);
				jobAdvertisementDao.save(jobAdvertisement);
				return new SuccessResult("İş ilanı onayı geri alındı yapıldı");
			}else {
				return new SuccessResult("İş ilanı zaten onaysız");
			}
			
			
			
		}else {
			return new ErrorResult("İş ilanı bulunamadı");
		}
	}

	@Override
	public Result addAdvertisement(JobAdvertisement jobAdvertisement, int employerId) {
		
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
		
		jobAdvertisement.setReleaseDate(new Date());
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
				
				if(typeOfWorkDao.findById(jobAdvertisement.getTypeOfWork().getId()) !=null){
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
				
				
				
				if(jobAdvertisement.getExJobAdvertisementId()==0) {
					jobAdvertisements.add(jobAdvertisement);

					employer.setJobAdvertisements(jobAdvertisements);
					employerDao.save(employer);
					return new SuccessResult("İş ilanı kayıt edildi.");		
				}else {
					for(JobAdvertisement advertisement: employer.getJobAdvertisements()) {
						if(advertisement.getId()==jobAdvertisement.getExJobAdvertisementId()) {
								
							
							jobAdvertisements.add(jobAdvertisement);
								
							employer.setJobAdvertisements(jobAdvertisements);			
							
							employerDao.save(employer);
							
							return new SuccessResult("İş ilanı güncellenmesi için istek gönderildi.");		
						}
					}
					return new SuccessResult("Eski iş ilanı bulunamadı");
				}		
										
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

	public Result jobAdvertisementUpdateApprove(int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId);
		if(jobAdvertisement==null) {
			return new ErrorResult("İş ilanı mevcut değil");
		}
		if(jobAdvertisement.getExJobAdvertisementId()!=0) {
		jobAdvertisement.setId(jobAdvertisement.getExJobAdvertisementId());
		jobAdvertisement.setExJobAdvertisementId(0);
		jobAdvertisement.setApproved(true);
		jobAdvertisementDao.deleteById(jobAdvertisementId);
		jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Güncelleme onaylandı");
		}else {
			return new SuccessResult("İlan güncelleme beklemiyor");
		}
		
		
		
	}
	public DataResult<JobAdvertisement> getJobAdvertisementById(int id){
	 return new SuccessDataResult<JobAdvertisement>(jobAdvertisementDao.findById(id),"İş ilanı ayrıntısı getirildi");	
	}
	
	public DataResult<List<JobAdvertisement>> findAllByIsJobAdvertisementOpenAndApproved(boolean isOpen,boolean approved,int pageNumber,int pageSize){
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		return new SuccessDataResult<List<JobAdvertisement>>(jobAdvertisementDao.findAllByIsJobAdvertisementOpenAndApproved(isOpen, approved, pageable));
	}
	
	public Long countByIsJobAdvertisementOpenAndApproved(boolean isOpen,boolean approved) {
		return jobAdvertisementDao.countByIsJobAdvertisementOpenAndApproved(isOpen, approved);
	}
	
	public Long countByIsOpenAndApprovedAndCityAndTypeOfWork(boolean isOpen,boolean approved, String city, String typeOfWork) {
		return jobAdvertisementDao.countByIsOpenAndApprovedAndCityAndTypeOfWork(isOpen, approved, city, typeOfWork);
	}
	
	
}
