package com.kodlamaio.hrms.entities.conretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="telefon_numaralari")
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	int id;
	
	@Column(name= "alan_kodu")
	String areaCode;
	
	@Column(name= "telefon_numarasi")
	String phoneNumber;
	
	@Column(name="eski_telefon_numarasi_id")
	private int exPhoneNumberId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="is_verenler_id",referencedColumnName = "id",nullable = false)
	private Employer employer;
	

}
