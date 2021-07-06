package com.kodlamaio.hrms.core.upload;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;

public class CloudinaryAdapter {

	private static Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
			"cloud_name" , "dptyd0j4m",
			"api_key" , "849911617431147",
			"api_secret" , "Fe7T8-P_sLeL2qaKtXrp8A8eVs0"
			));
	
	public static DataResult<Map> uploadImage(MultipartFile file){
		Map uploadResult;
		try {
			uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());	
			
			return new SuccessDataResult<Map>(uploadResult,"Resim yüklendi");
		} catch (IOException e) {
			
			return new ErrorDataResult<Map>("Resim yüklenirken hata.");

		}
		
	}
}

