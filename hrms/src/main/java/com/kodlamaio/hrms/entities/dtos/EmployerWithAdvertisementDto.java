package com.kodlamaio.hrms.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerWithAdvertisementDto {
	
	private int id;
	private int employerId;
	private String companyName;
	private int openPositions;
	private Date applicationDeadline;
	private Date releaseDate;
	private String positionName;
	private boolean isJobAdvertisementOpen;
	private boolean approved;
}
