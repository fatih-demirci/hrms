package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.TypeOfWork;

public interface TypeOfWorkDao extends JpaRepository<TypeOfWork, Integer>{
	TypeOfWork findById(int id);

}
