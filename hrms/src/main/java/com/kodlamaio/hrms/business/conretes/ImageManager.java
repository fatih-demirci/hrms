package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kodlamaio.hrms.business.abstracts.ImageService;
import com.kodlamaio.hrms.core.upload.CloudinaryAdapter;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.ImageDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrms.entities.conretes.Image;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

@Service
public class ImageManager implements ImageService{

	private ImageDao imageDao;
	private JobSeekerDao jobSeekerDao;

	@Autowired
	public ImageManager(ImageDao imageDao,JobSeekerDao jobSeekerDao) {
		this.imageDao = imageDao;
		this.jobSeekerDao=jobSeekerDao;
	}

	@Override
	public Result uploadImage(int jobSeekerId, MultipartFile file) {
		
		JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId);
		
		if(jobSeeker.getCv()!=null) {
		String uploadResult = CloudinaryAdapter.uploadImage(file).getData().toString();
		
		String[] result = uploadResult.toString().split(",");
		
		String link ="";
		
		for(int i = 12; i< result[3].length();i++) {
			link+=result[3].charAt(i);
		}

		Image availableImage=null;
		if(jobSeeker.getCv().getImage()!=null) {
		availableImage = imageDao.findById(jobSeeker.getCv().getImage().getId());
		}
		if(availableImage!=null) {
			availableImage.setImageURL(link);
			jobSeeker.getCv().setImage(availableImage);
		}else {
			
		Image image = new Image();
		image.setImageURL(link);
		
		jobSeeker.getCv().setImage(image);
		
		
	}
		jobSeekerDao.save(jobSeeker);
		return new SuccessResult(link);
		
		}else {
			return new ErrorResult("İş arayanın cv'si mevcut değil");
		}
		
	}
	
	

}
