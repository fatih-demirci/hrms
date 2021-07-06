package com.kodlamaio.hrms.core.sort;

import java.util.Comparator;

import com.kodlamaio.hrms.entities.conretes.WorkExperience;

public class SortByQuitDate implements Comparator<WorkExperience>{
	
	@Override
	public int compare(WorkExperience o1, WorkExperience o2) {
			
		System.out.println((o2.getQuitDate().getYear()*3.65+o2.getQuitDate().getMonth()*0.30+o2.getQuitDate().getDay()*0.01) - (o1.getQuitDate().getYear()*3.65+o1.getQuitDate().getMonth()*0.30+o1.getQuitDate().getDay()*0.01));	
		
		return (int)((o2.getQuitDate().getYear()*3.65+o2.getQuitDate().getMonth()*0.30+o2.getQuitDate().getDay()*0.01) - (o1.getQuitDate().getYear()*3.65+o1.getQuitDate().getMonth()*0.30+o1.getQuitDate().getDay()*0.01));

		
	}
}
