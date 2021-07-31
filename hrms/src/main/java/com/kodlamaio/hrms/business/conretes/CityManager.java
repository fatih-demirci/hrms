package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.CityService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.CityDao;
import com.kodlamaio.hrms.entities.conretes.City;

@Service
public class CityManager implements CityService{

	CityDao cityDao;
	
	@Autowired
	public CityManager(CityDao cityDao) {
		this.cityDao=cityDao;
	}
	
	public DataResult<List<City>> getAll(){
		
		return new SuccessDataResult<List<City>>(cityDao.findAll(),"Şehirler Listelendi");
		
	}
}
