package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;


public interface EmployerService {

	SuccessDataResult<List<Employer>> getAll();
	
	SuccessDataResult<List<Employer>> getAllJobAdvertisements(List<Employer> employers);
	
	DataResult<Employer> getAllEmployersJobAdvertisements(int id);
	
	DataResult<List<Employer>>  getAllJobAdvertisementSorted();
	
	Result add(Employer employer);
	
	DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetails();
}
