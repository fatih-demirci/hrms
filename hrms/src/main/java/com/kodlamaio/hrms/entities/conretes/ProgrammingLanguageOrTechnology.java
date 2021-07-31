package com.kodlamaio.hrms.entities.conretes;

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
@Table(name="programlama_dilleri_teknolojileri")
public class ProgrammingLanguageOrTechnology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="programlama_teknoloji_adi")
	@NotNull
	@NotEmpty
	String name;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="oz_gecmisler_id",referencedColumnName = "id",nullable = false)
	private Cv cv;
}
