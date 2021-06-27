package com.kodlamaio.hrms.business.conretes;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.WorkExperienceService;
import com.kodlamaio.hrms.core.sort.SortByGraduationDate;
import com.kodlamaio.hrms.core.sort.SortByQuitDate;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;
import com.kodlamaio.hrms.entities.conretes.School;
import com.kodlamaio.hrms.entities.conretes.WorkExperience;

@Service
public class WorkExperienceManager implements WorkExperienceService{

	JobSeekerDao jobSeekerDao;
	
	@Autowired
	public WorkExperienceManager(JobSeekerDao jobSeekerDao) {
		this.jobSeekerDao=jobSeekerDao;
	}
	
	
	@Override
	public DataResult<List<WorkExperience>> getAllWorkExperienceSorted(int id) {
		
		JobSeeker jobSeeker = jobSeekerDao.findById(id);
		List<WorkExperience> experiences = jobSeeker.getCv().getWorkExperience();

		for (WorkExperience workExperience : experiences) {
			if (workExperience.getQuitDate() == null) {
				workExperience.setQuitDate (new Date(2100,0,0));
			}

		}
			
			Collections.sort(experiences,new SortByQuitDate());

			return new SuccessDataResult<List<WorkExperience>>(experiences, "Tecrübeler sıralı listelendi.");
			
	}
	
	

}
