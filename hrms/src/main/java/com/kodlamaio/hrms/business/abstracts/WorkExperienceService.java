package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.WorkExperience;


public interface WorkExperienceService {
	public DataResult<List<WorkExperience>> getAllWorkExperienceSorted(int id);
}
