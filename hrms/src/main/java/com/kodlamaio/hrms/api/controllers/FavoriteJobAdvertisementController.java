package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.hrms.business.abstracts.FavoriteJobAdvertisementService;
import com.kodlamaio.hrms.core.utilities.result.Result;

@RestController
@RequestMapping("/FavoriteJobAdvertisementController")
@CrossOrigin
public class FavoriteJobAdvertisementController {
	FavoriteJobAdvertisementService favoriteJobAdvertisementService;
	
	@Autowired
	public FavoriteJobAdvertisementController(FavoriteJobAdvertisementService favoriteJobAdvertisementService) {
		this.favoriteJobAdvertisementService=favoriteJobAdvertisementService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestParam int jobSeekerId,@RequestParam int jobAdvertisementId) {
		return favoriteJobAdvertisementService.add(jobSeekerId, jobAdvertisementId);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestParam int jobSeekerId,@RequestParam int jobAdvertisementId) {
		return favoriteJobAdvertisementService.delete(jobSeekerId, jobAdvertisementId);
	}
}
