package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.SystemStaff;

public interface SystemStaffService {
	Result add(SystemStaff systemStaff);
	public DataResult<SystemStaff> getSystemStaff(int id);
}
