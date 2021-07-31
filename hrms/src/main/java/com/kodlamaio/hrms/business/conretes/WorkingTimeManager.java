package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.WorkingTimeService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.WorkingTimeDao;
import com.kodlamaio.hrms.entities.conretes.WorkingTime;

@Service
public class WorkingTimeManager implements WorkingTimeService{
	
	WorkingTimeDao workingTimeDao;
	
	public WorkingTimeManager(WorkingTimeDao workingTimeDao) {
		this.workingTimeDao=workingTimeDao;
	}
	
	@Override
	public DataResult<List<WorkingTime>> getAll(){
		return new SuccessDataResult<List<WorkingTime>>(workingTimeDao.findAll());
	}
	

}
