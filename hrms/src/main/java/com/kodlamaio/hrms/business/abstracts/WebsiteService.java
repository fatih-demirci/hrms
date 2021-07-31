package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Website;

public interface WebsiteService {

	public DataResult<Website> isUpdate(int id);
	public Result approve(int employerId,int websiteId);
}
