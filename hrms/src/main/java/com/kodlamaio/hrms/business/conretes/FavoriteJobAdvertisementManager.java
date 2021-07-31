package com.kodlamaio.hrms.business.conretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.FavoriteJobAdvertisementService;
import com.kodlamaio.hrms.core.utilities.result.ErrorDataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.FavoriteJobAdvertisementDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import com.kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrms.entities.conretes.FavoriteJobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.JobAdvertisement;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

@Service
public class FavoriteJobAdvertisementManager implements FavoriteJobAdvertisementService{
	FavoriteJobAdvertisementDao favoriteJobAdvertisementDao;
	JobAdvertisementDao jobAdvertisementDao;
	JobSeekerDao jobSeekerDao;
	
	@Autowired
	public FavoriteJobAdvertisementManager(FavoriteJobAdvertisementDao favoriteJobAdvertisementDao,
			JobAdvertisementDao jobAdvertisementDao,
			JobSeekerDao jobSeekerDao) {
		this.favoriteJobAdvertisementDao=favoriteJobAdvertisementDao;
		this.jobAdvertisementDao=jobAdvertisementDao;
		this.jobSeekerDao=jobSeekerDao;
	}

	@Override
	public Result add(int jobSeekerId, int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId);
		if(jobAdvertisement!=null) {
			JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId);
			if(jobSeeker==null) {
				return new ErrorResult("İş Veren Bulunamadı");
			}
			List<FavoriteJobAdvertisement> favoriteJobAdvertisements;
			if(jobSeeker.getFavoriteJobAdvertisement()==null) {
				favoriteJobAdvertisements = new ArrayList<>();
			}else {
				favoriteJobAdvertisements = jobSeeker.getFavoriteJobAdvertisement();
				for(FavoriteJobAdvertisement favoriteJobAdvertisement: favoriteJobAdvertisements) {
					if(favoriteJobAdvertisement.getJobAdvertisementId()==jobAdvertisementId) {
						return new ErrorResult("Zaten Kayıtlı");
					}
					
				}
			}
			FavoriteJobAdvertisement favoriteJobAdvertisement = new FavoriteJobAdvertisement();
			favoriteJobAdvertisement.setJobAdvertisementId(jobAdvertisementId);
			favoriteJobAdvertisement.setJobSeeker(jobSeeker);
			favoriteJobAdvertisements.add(favoriteJobAdvertisement);
			
			jobSeeker.setFavoriteJobAdvertisement(favoriteJobAdvertisements);
			jobSeekerDao.save(jobSeeker);
			return new SuccessResult("Favorilere Kayıt Edildi");
		}else {
			return new ErrorResult("İş İlanı bulunamadı");
		}
		
		
	
	}

	@Override
	public Result delete(int jobSeekerId, int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement = jobAdvertisementDao.findById(jobAdvertisementId);
		if(jobAdvertisement!=null) {
			JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId);
			if(jobSeeker==null) {
				return new ErrorResult("İş Arayan Bulunamadı");
			}
			
			List<FavoriteJobAdvertisement> favoriteJobAdvertisements;
			if(jobSeeker.getFavoriteJobAdvertisement()==null) {
				return new ErrorResult("Favorilerde Hiç İş İlanı Yok");
			}else {
				favoriteJobAdvertisements = jobSeeker.getFavoriteJobAdvertisement();
				for(FavoriteJobAdvertisement favoriteJobAdvertisement: favoriteJobAdvertisements) {
					if(favoriteJobAdvertisement.getJobAdvertisementId()==jobAdvertisementId) {
						favoriteJobAdvertisements.remove(favoriteJobAdvertisement);
						jobSeeker.setFavoriteJobAdvertisement(favoriteJobAdvertisements);
						favoriteJobAdvertisementDao.deleteById(favoriteJobAdvertisement.getId());
						return new ErrorResult("Favorilerden Silindi");
					}
					
				}
				return new ErrorResult("Favorilerde bu iş ilanı yok");
			}
			
			
		}else {
			return new ErrorResult("İş İlanı bulunamadı");
		}
		
		
		}
	
	
	
	
	}
	
	

