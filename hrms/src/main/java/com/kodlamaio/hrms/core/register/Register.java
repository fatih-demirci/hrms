package com.kodlamaio.hrms.core.register;

import java.util.ArrayList;
import java.util.List;

import com.kodlamaio.hrms.entities.conretes.Employer;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;
import com.kodlamaio.hrms.entities.conretes.Website;

public class Register {
	
	public static Employer normalizeEmployer(Employer employer) {
		
		String eMail = employer.getMember().getEMail().toLowerCase();
		employer.getMember().setEMail(eMail);
		
		String CompanyName =employer.getCompanyName().toLowerCase();
		employer.setCompanyName(CompanyName);
		
		List<Website> websites = new ArrayList<Website>();
		for(Website website : employer.getWebsites()) {
			website.setWebsite(website.getWebsite().toLowerCase());
			websites.add(website) ;
		}
		employer.setWebsites(websites);
		
		return employer;
	}
	
	
	public static JobSeeker normalizeJobSeeker(JobSeeker jobSeeker) {
		jobSeeker.setName(jobSeeker.getName().toLowerCase());
		
		jobSeeker.setLastName(jobSeeker.getLastName().toLowerCase());
		
		jobSeeker.getMember().setEMail(jobSeeker.getMember().getEMail().toLowerCase());
		
		return jobSeeker;
	}
	
}
