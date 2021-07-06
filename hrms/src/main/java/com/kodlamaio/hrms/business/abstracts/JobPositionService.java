package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.entities.conretes.JobPosition;

public interface JobPositionService {

	SuccessDataResult<List<JobPosition>> getAll();
	Result add(JobPosition jobPosition);
	
}
