package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.WorkingTime;

public interface WorkingTimeService {
	public DataResult<List<WorkingTime>> getAll();
}
