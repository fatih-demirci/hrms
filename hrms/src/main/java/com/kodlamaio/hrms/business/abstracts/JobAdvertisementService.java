package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;


public interface JobAdvertisementService {
	Result jobAdvertisementOpen(int jobAdvertisementId,boolean isJobAdvertisementOpen);
	Result addAdvertisement(JobAdvertisement jobAdvertisement,int employerId);
	public DataResult<JobAdvertisement> getJobAdvertisementById(int id);
	public Result jobAdvertisementApprove(int jobAdvertisementId,boolean isJobAdvertisementApprove);
}
