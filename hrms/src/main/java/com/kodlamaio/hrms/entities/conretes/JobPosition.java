package com.kodlamaio.hrms.entities.conretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Is_pozisyonu")
public class JobPosition {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	int id;
	
	@Column(name="isim")
	String name;
	
	
}
