package com.kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kodlamaio.hrms.business.abstracts.ImageService;
import com.kodlamaio.hrms.core.utilities.result.Result;

@RestController
@RequestMapping("/api/image")
public class ImageController {

	private ImageService imageService;
	
	@Autowired
	public ImageController(ImageService imageService) {
		this.imageService=imageService;
	}
	
	@PostMapping("/uploadImage")
	public Result uploadImage(@RequestParam int jobSeekerId,@RequestParam MultipartFile file) {
		return this.imageService.uploadImage(jobSeekerId,file);
		
	}
}
