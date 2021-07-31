package com.kodlamaio.hrms.business.conretes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.SchoolService;
import com.kodlamaio.hrms.core.sort.SortByGraduationDate;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrms.dataAccess.abstracts.SchoolDao;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;
import com.kodlamaio.hrms.entities.conretes.School;

@Service
public class SchoolManager implements SchoolService {

	SchoolDao schoolDao;
	JobSeekerDao jobSeekerDao;

	public SchoolManager(SchoolDao schoolDao, JobSeekerDao jobSeekerDao) {
		this.schoolDao = schoolDao;
		this.jobSeekerDao = jobSeekerDao;
	}

	@Override
	public DataResult<List<School>> getAllSchoolSorted(int id) {

		JobSeeker jobSeeker = jobSeekerDao.findById(id);
		List<School> schools = jobSeeker.getCv().getSchool();

		for (School school : schools) {
			if (school.getGraduationDate() == null) {
				school.setGraduationDate(new Date(Long.MAX_VALUE));
			}

		}
		
			Collections.sort(schools, new SortByGraduationDate());

			return new SuccessDataResult<List<School>>(schools, "Okullar sıralı listelendi");

	}
}
