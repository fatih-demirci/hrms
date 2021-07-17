package com.kodlamaio.hrms.business.abstracts;

import java.util.List;

import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.entities.conretes.School;

public interface SchoolService {
	public DataResult <List<School>> getAllSchoolSorted(int id);
}
