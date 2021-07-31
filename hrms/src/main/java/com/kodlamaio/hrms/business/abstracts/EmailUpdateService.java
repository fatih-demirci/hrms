package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.EmailUpdate;

public interface EmailUpdateService {
	Result update(int employerId , EmailUpdate emailUpdate);
	public Result approve(int employerId);
}
