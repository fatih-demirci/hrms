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
@NoArgsConstructor
@Entity
@Table(name = "is_verenler", schema = "public")

public class Employer{
	
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
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<PhoneNumber> phoneNumbers;
	
	@OneToMany(mappedBy = "employer",cascade = CascadeType.ALL)
	private List<Website> websites;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "uyeler_id",referencedColumnName = "id_uyeler")
	private Member member;
	
	@OneToMany(mappedBy = "employer" ,cascade = CascadeType.ALL)
	private List<JobAdvertisement> jobAdvertisements;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="sirket_adi_guncelleme_id",referencedColumnName = "id")
	private CompanyNameUpdate companyNameUpdate;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="eposta_guncelleme",referencedColumnName = "id")
	private EmailUpdate emailUpdate;

}

