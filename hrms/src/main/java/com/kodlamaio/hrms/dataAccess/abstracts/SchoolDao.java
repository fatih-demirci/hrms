package com.kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.School;

public interface SchoolDao extends JpaRepository<School, Integer>{
  List<School> findById(int id);
}
