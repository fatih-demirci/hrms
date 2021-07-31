package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.PhoneNumber;
import com.kodlamaio.hrms.entities.conretes.Website;
import com.kodlamaio.hrms.entities.dtos.EmployerWithAdvertisementDto;


public interface EmployerService {

	SuccessDataResult<List<Employer>> getAll();
	
	SuccessDataResult<List<Employer>> getAllJobAdvertisements(List<Employer> employers);
	
	DataResult<Employer> getAllEmployerJobAdvertisements(int id);
	
	DataResult<List<Employer>>  getAllJobAdvertisementSorted();
	
	Result add(Employer employer);
	
	DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetails(boolean isOpen,boolean approved,int pageNo, int pageSize);
	
	DataResult<List<EmployerWithAdvertisementDto>> getEmployerWithAdvertisementDetailsByCityAndTypeOfWork(boolean isOpen, boolean approved,int pageNo, int pageSize, String city, String typeOfWork);
																												
	public Result updatePassword(int employerId,String password,String passwordRepeat);
	
	public Result updateWebsite(int employerId , Website website);
	
	public Result updatePhoneNumber(int employerId, PhoneNumber phoneNumber);
}
