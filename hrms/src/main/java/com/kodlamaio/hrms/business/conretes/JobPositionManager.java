package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.JobPositionService;
import com.kodlamaio.hrms.core.utilities.SaveNormalizer;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import com.kodlamaio.hrms.entities.conretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService{

	private JobPositionDao jobPositionDao;

	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao=jobPositionDao;

	}
	
	@Override
	public SuccessDataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll(), "Data Listelendi");
				
	}
	
	public void save() {
		
	}

	@Override
	public Result add(JobPosition jobPosition) {
		String name = SaveNormalizer.setString(jobPosition.getName());
		if(jobPositionDao.findByName(name)==null) {
			jobPosition.setName(name);
			jobPositionDao.save(jobPosition);
		return new SuccessResult("İş pozisyonu Eklendi");
		}else {
			return new ErrorResult("İş pozisyon Zaten Mevcut");
		}
		
		
		
	}

	
}
