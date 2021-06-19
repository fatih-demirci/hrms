package com.kodlamaio.hrms.core.register;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

public class Register {
	
	public static Employer normalizeEmployer(Employer employer) {
		
		String eMail = employer.getMember().getEMail().toLowerCase();
		employer.getMember().setEMail(eMail);
		
		String CompanyName =employer.getCompanyName().toLowerCase();
		employer.setCompanyName(CompanyName);
		
		String webSite = employer.getWebsite().toLowerCase();
		employer.setWebsite(webSite);
		
		return employer;
	}
	
	
	public static JobSeeker normalizeJobSeeker(JobSeeker jobSeeker) {
		jobSeeker.setName(jobSeeker.getName().toLowerCase());
		
		jobSeeker.setLastName(jobSeeker.getLastName().toLowerCase());
		
		jobSeeker.getMember().setEMail(jobSeeker.getMember().getEMail().toLowerCase());
		
		return jobSeeker;
	}
	
}
