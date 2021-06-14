package com.kodlamaio.hrms.entities.conretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="uyeler")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","is_verenler"})
public class Member{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_uyeler")
	int id;
	

	@Column(name="e_posta")
	String eMail;
	
	@Column(name="sifre")
	String password;
	
	String passwordRepeat;
	
	
}
