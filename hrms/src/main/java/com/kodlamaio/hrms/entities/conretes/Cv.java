package com.kodlamaio.hrms.entities.conretes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="oz_gecmisler")
public class Cv {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="github_adresi")
	private String githubAdress;
	
	@Column(name="linkedin_adresi")
	private String linkedinAdress;
	
	@Column(name="on_tanim")
	private String description;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
	private List<School> school;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
	private List<Language> language;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
	private List<ProgrammingLanguageOrTechnology> programmingLanguageOrTechnology;
	
	@OneToMany(mappedBy = "cv",cascade = CascadeType.ALL)
	private List<WorkExperience> workExperience;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "resimler_id",referencedColumnName = "id")
	private Image image;
	
	

}
