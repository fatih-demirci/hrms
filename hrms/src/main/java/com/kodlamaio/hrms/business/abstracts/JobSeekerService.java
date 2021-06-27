package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;
import com.kodlamaio.hrms.entities.conretes.School;

public interface JobSeekerService {

	DataResult<List<JobSeeker>> getall();
	Result add(JobSeeker jobSeeker);
	public Result addCv(int jobSeekerId, Cv cv);
	public DataResult<Cv> getCv(int jobSeekerId);
}
