package com.kodlamaio.hrms.entities.conretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="is_pozisyonlari")
public class JobPosition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="isim")
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "jobPosition" ,cascade = CascadeType.ALL)
	private List<JobAdvertisement> jobAdvertisements;
	
    //public JobPosition() {
	//	jobAdvertisements = new ArrayList<JobAdvertisement>();
	//}
	
}
