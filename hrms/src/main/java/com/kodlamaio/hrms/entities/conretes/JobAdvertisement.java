package com.kodlamaio.hrms.entities.conretes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor

@Entity
@Table(name="is_ilani")
public class JobAdvertisement {
	
	public JobAdvertisement() {
		exJobAdvertisementId=0;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="is_tanimi")
	String jobDescription;
	
	@Column(name="maas_min")
	int minSalary;
	
	@Column(name="maas_max")
	int maxSalary;
	
	@Column(name="acik_pozisyon_sayisi")
	int openPositions;
	
	@Column(name="son_basvuru_tarihi")
	Date applicationDeadline;
	
	@Column(name="yayin_tarihi")
	Date releaseDate;
	
	@Column(name="is_ilani_acik")
	boolean isJobAdvertisementOpen;
	
	@Column(name="personel_onayli")
	boolean approved;
	
	@Column(name="eski_is_ilani_id")
	int exJobAdvertisementId;
	
	@ManyToOne
	@JoinColumn(name="is_pozisyonu_id",referencedColumnName = "id")
	private JobPosition jobPosition;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="is_verenler_id",referencedColumnName = "id")
	private Employer employer;
	
	@ManyToOne
	@JoinColumn(name="sehirler_id",referencedColumnName = "id")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "calisma_turu_id", referencedColumnName = "id")
	private TypeOfWork typeOfWork;
	
	@ManyToOne
	@JoinColumn(name="calisma_zamani_id",referencedColumnName = "id")
	private WorkingTime workingTime;

}
