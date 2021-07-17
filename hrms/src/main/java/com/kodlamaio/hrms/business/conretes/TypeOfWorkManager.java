package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.TypeOfWorkService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.TypeOfWorkDao;
import com.kodlamaio.hrms.entities.conretes.TypeOfWork;

@Service
public class TypeOfWorkManager implements TypeOfWorkService{
	
	TypeOfWorkDao typeOfWorkDao;
	
	@Autowired
	public TypeOfWorkManager(TypeOfWorkDao typeOfWorkDao) {
		this.typeOfWorkDao=typeOfWorkDao;
	}
	
	public DataResult<List<TypeOfWork>> getAll(){
		return new SuccessDataResult<List<TypeOfWork>>(typeOfWorkDao.findAll(),"İş türleri listelendi");
	}
}
