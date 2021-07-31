package com.kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.entities.conretes.FavoriteJobAdvertisement;

public interface FavoriteJobAdvertisementDao extends JpaRepository<FavoriteJobAdvertisement, Integer> {
	Result deleteById(int id);
}
