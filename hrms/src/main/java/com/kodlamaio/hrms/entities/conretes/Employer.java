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

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "is_verenler", schema = "public")
public class Employer{
	
	public Employer() {
		phoneNumbers = new ArrayList<PhoneNumber>();
		jobAdvertisements = new ArrayList<JobAdvertisement>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "employer_seq")
	@Column(name= "id")
	int id;
	
	@Column(name= "sirket_adi")
	String companyName;
	
	@Column(name= "web_sitesi")
	String website;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mail_dogrulama_id")
	private EMailVerification eMailVerification;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="personel_onay_id")
	private StaffConfirmation staffConfirmation;
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<PhoneNumber> phoneNumbers;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "uyeler_id")
	private Member member;
	
	@OneToMany(mappedBy = "employer" ,cascade = CascadeType.ALL)
	private List<JobAdvertisement> jobAdvertisements;

}

