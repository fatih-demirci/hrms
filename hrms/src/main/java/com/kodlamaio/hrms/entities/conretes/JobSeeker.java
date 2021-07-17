package com.kodlamaio.hrms.entities.conretes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="is_arayanlar")
public class JobSeeker{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	int id;
	
	@Column(name="ad")
	@NotNull
	@NotEmpty
	String name;
	
	@Column(name="soyad")
	@NotNull
	@NotEmpty
	String lastName;
	
	@Column(name="tcno")
	@NotEmpty
	@NotNull
	String nationalIdentity;
	
	@Column(name="dogum_yili")
	@NotNull
	Date birthDay;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="mail_dogrulama_id")
	private EMailVerification eMailVerification;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "uyeler_id")
	private Member member;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "oz_gecmisler_id")
	private Cv cv;
	

}
