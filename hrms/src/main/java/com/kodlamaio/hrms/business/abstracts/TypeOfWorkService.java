package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.TypeOfWork;

public interface TypeOfWorkService {
	public DataResult<List<TypeOfWork>> getAll();
}
