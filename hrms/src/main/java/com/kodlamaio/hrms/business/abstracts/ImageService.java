package com.kodlamaio.hrms.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import com.kodlamaio.hrms.core.utilities.result.Result;

public interface ImageService {
	Result uploadImage(int jobSeekerId,MultipartFile file);

}
