package com.kodlamaio.hrms.entities.conretes;

import java.util.ArrayList;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@Entity
@Table(name = "is_verenler", schema = "public")
//@JsonIgnoreProperties("jobAdvertisements")
public class Employer{
	
	public Employer() {
		jobAdvertisements = new ArrayList<JobAdvertisement>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	int id;
	
	@NotEmpty
	@NotNull
	@Column(name= "sirket_adi")
	String companyName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mail_dogrulama_id")
	private EMailVerification eMailVerification;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="personel_onay_id")
	private StaffConfirmation staffConfirmation;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<PhoneNumber> phoneNumbers;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<Website> websites;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "uyeler_id",referencedColumnName = "id_uyeler")
	private Member member;
	
	@OneToMany(mappedBy = "employer" ,cascade = CascadeType.ALL)
	private List<JobAdvertisement> jobAdvertisements;

}

