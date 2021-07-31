package com.kodlamaio.hrms.core.sort;

import java.util.Comparator;

import com.kodlamaio.hrms.entities.conretes.School;

public class SortByGraduationDate implements Comparator<School>{

	@Override
	public int compare(School o1, School o2) {
		System.out.println((o2.getGraduationDate().getYear()*3.65+o2.getGraduationDate().getMonth()*0.30+o2.getGraduationDate().getDay()*0.01) - (o1.getGraduationDate().getYear()*3.65+o1.getGraduationDate().getMonth()*0.30+o1.getGraduationDate().getDay()*0.01));	
		
			return (int)((o2.getGraduationDate().getYear()*3.65+o2.getGraduationDate().getMonth()*0.30+o2.getGraduationDate().getDay()*0.01) - (o1.getGraduationDate().getYear()*3.65+o1.getGraduationDate().getMonth()*0.30+o1.getGraduationDate().getDay()*0.01));

		
	}

	
}