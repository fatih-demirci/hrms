package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

public interface JobSeekerService {

	DataResult<List<JobSeeker>> getall();
	Result add(JobSeeker jobSeeker);
}
