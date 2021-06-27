package com.kodlamaio.hrms.business.abstracts;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.Cv;


public interface CvService {
	Result add(int jobSeekerId,Cv cv);
}
