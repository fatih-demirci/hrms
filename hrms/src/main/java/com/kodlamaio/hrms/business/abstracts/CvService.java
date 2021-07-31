package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.dtos.CvWithJobSeekerDto;

public interface CvService {
	public DataResult<List<Cv>> getAll();
	public DataResult<List<CvWithJobSeekerDto>> cvWithJobSeekerDetails();
}
