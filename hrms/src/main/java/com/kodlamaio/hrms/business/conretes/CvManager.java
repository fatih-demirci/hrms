package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.CvService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.CvDao;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.dtos.CvWithJobSeekerDto;

@Service
public class CvManager implements CvService{
	private CvDao cvDao;
	
	@Autowired
	public CvManager(CvDao cvDao) {
		this.cvDao=cvDao;
	}
	
	public DataResult<List<Cv>> getAll() {
		return new SuccessDataResult<List<Cv>>(cvDao.findAll());
	}
	
	public DataResult<List<CvWithJobSeekerDto>> cvWithJobSeekerDetails(){
		return new SuccessDataResult<List<CvWithJobSeekerDto>>(cvDao.cvWithJobSeekerDetails(),"Listelendi");
	}
}
