package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.CompanyNameUpdate;

public interface CompanyNameUpdateService {
	Result update(int employerId ,CompanyNameUpdate companyNameUpdate);
	public Result approve(int employerId);
}
