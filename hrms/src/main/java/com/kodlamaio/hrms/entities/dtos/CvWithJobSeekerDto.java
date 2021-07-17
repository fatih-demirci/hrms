package com.kodlamaio.hrms.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvWithJobSeekerDto {
	
	private int id;
	private String name;
	private String lastName;
	private Date birthDay;
	private int cvId;
	private String eMail;
	private String imageURL;
	
}
