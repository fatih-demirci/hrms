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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="okul_bolumler")
public class School {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="okul_adi")
	@NotNull
	@NotEmpty
	private String schoolName;
	
	@Column(name="bolum_adi")
	@NotNull
	@NotEmpty
	private String department;
	
	@NotEmpty
	@NotNull
	@Column(name="okul_giris")
	private Date schoolEntryDate;
	
	@Column(name="okul_mezun")
	private Date graduationDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="oz_gecmisler_id",referencedColumnName = "id",nullable = false)
	private Cv cv;

}
