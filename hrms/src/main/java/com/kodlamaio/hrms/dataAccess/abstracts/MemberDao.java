package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.entities.conretes.Member;

public interface MemberDao extends JpaRepository<Member, Integer>{
	Member findByeMail(String eMail);
}
