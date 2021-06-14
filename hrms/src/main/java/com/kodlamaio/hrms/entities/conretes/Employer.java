package com.kodlamaio.hrms.entities.conretes;


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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "is_verenler", schema = "public")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","telefon_numaralari"})
public class Employer{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	int id;
	
	@Column(name= "sirket_adi")
	String companyName;
	
	@Column(name= "web_sitesi")
	String website;
	
	@OneToMany(mappedBy = "employer")
	private List<PhoneNumber> phoneNumbers;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "uyeler_id")
	private Member member;

}

