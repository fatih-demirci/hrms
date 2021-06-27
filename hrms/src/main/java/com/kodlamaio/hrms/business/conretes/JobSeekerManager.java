package com.kodlamaio.hrms.business.conretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.kodlamaio.hrms.business.abstracts.JobSeekerService;

import com.kodlamaio.hrms.core.mailvalidate.MailValidService;
import com.kodlamaio.hrms.core.mailvalidation.MailValidationService;
import com.kodlamaio.hrms.core.mernis.PersonValidationService;
import com.kodlamaio.hrms.core.register.Register;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import com.kodlamaio.hrms.entities.conretes.Cv;
import com.kodlamaio.hrms.entities.conretes.JobSeeker;

@Service
public class JobSeekerManager implements JobSeekerService {

	JobSeekerDao jobSeekerDao;
	PersonValidationService personValidation;
	MailValidService mailValidService;
	MailValidationService mailValidationService;

	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekerDao, PersonValidationService personValidation,
			MailValidService mailValidService, MailValidationService mailValidationService) {
		this.jobSeekerDao = jobSeekerDao;
		this.personValidation = personValidation;
		this.mailValidService = mailValidService;
		this.mailValidationService = mailValidationService;
	}

	@Override
	public DataResult<List<JobSeeker>> getall() {
		return new SuccessDataResult<List<JobSeeker>>(jobSeekerDao.findAll(), "Data Listelendi");

	}

	@Override
	public Result add(@RequestBody JobSeeker jobSeeker) {
		boolean nameIsEmpty = true;
		boolean lastnameIsEmpty = true;
		boolean nationalIdentityIsEmpty = true;
		boolean nationalIdentityisValid = false;
		boolean nationalIdentityIsUsed = true;
		boolean dateOfBirthIsEmpty = true;
		boolean eMailIsEmpty = true;
		boolean eMailIsUsed = true;
		boolean mailIsValid = false;
		boolean passwordIsEmpty = true;
		boolean passwordsSame = false;

		String error = "";

		jobSeeker = Register.normalizeJobSeeker(jobSeeker);
		if (!jobSeeker.getName().isEmpty()) {
			nameIsEmpty = false;
		} else {
			error += " İsim boş olamaz.";
		}

		if (!jobSeeker.getLastName().isEmpty()) {
			lastnameIsEmpty = false;
		} else {
			error += " Soyad boş olamaz.";
		}

		if (!jobSeeker.getNationalIdentity().isEmpty()) {
			nationalIdentityIsEmpty = false;
			if (jobSeeker.getNationalIdentity().length() == 11) {
				nationalIdentityisValid = true;
				if (jobSeekerDao.findByNationalIdentity(jobSeeker.getNationalIdentity()) == null) {
					nationalIdentityIsUsed = false;
				} else {
					error += " TC Kimlik numarası zaten kullanılıyor";
				}
			} else {
				error += " TC Kimlik numarası 11 hane olmalıdır.";
			}
		} else {
			error += " TC Kimlik numarası boş olamaz.";
		}

		if (jobSeeker.getBirthDay() != null) {
			dateOfBirthIsEmpty = false;
		} else {
			error += " Doğum tarihi boş olamaz.";
		}

		if (!jobSeeker.getMember().getEMail().isEmpty()) {
			eMailIsEmpty = false;
			if (mailValidService.mailIsValid(jobSeeker.getMember().getEMail())) {
				mailIsValid = true;

				if (jobSeekerDao.findByMember_eMail(jobSeeker.getMember().getEMail()) == null) {
					eMailIsUsed = false;

				} else {
					error += " e-mail adresi zaten kullanılıyor.";
				}

			} else {
				error += " Geçersiz e-mail adresi";
			}

		} else {
			error += " e-mail adresi boş olamaz.";
		}

		if (!jobSeeker.getMember().getPassword().isEmpty() && !jobSeeker.getMember().getPasswordRepeat().isEmpty()) {
			passwordIsEmpty = false;
			if (jobSeeker.getMember().getPassword().equals(jobSeeker.getMember().getPasswordRepeat())) {
				passwordsSame = true;

			} else {
				error += " Şifre ve şifre tekrar eşleşmiyor";
			}
		} else {
			error += " Şifre ve şifre tekrar alanları boş olamaz";
		}

		/*
		 * System.out.println("Name "+nameIsEmpty+ " "+ "Last name "+lastnameIsEmpty+
		 * " nationalIdentityIsEmpty "+nationalIdentityIsEmpty+
		 * " nationalIdentiyisValid "+nationalIdentiyisValid+
		 * " dateOfBirthIsEmpty "+dateOfBirthIsEmpty+ " eMailIsEmpty "+eMailIsEmpty+
		 * " eMailIsUsed "+eMailIsUsed+ " passwordIsEmpty "+passwordIsEmpty +
		 * " passwordsSame "+passwordsSame + " mailIsValid "+mailIsValid);
		 */

		if (!nameIsEmpty && !lastnameIsEmpty && !nationalIdentityIsEmpty && !dateOfBirthIsEmpty
				&& nationalIdentityisValid && !nationalIdentityIsUsed && !passwordIsEmpty && passwordsSame
				&& !eMailIsUsed && !eMailIsEmpty && mailIsValid) {
			if (personValidation.validate(Long.valueOf(jobSeeker.getNationalIdentity()),
					jobSeeker.getName().toUpperCase(), jobSeeker.getLastName().toUpperCase(),
					jobSeeker.getBirthDay().getYear())) {

				jobSeekerDao.save(jobSeeker);
				mailValidationService.sendMailValidation(jobSeeker.getMember().getEMail());
				return new SuccessResult("İş arayan eklendi");
			} else {
				error += " Girilen bilgiler gerçek bir insana ait değil.";
				return new ErrorResult(error);
			}
		} else {
			return new ErrorResult(error);
		}

	}

	@Override
	public Result addCv(int jobSeekerId, Cv cv) {

		JobSeeker jobSeeker = jobSeekerDao.findById(jobSeekerId);
		jobSeeker.setCv(cv);
		jobSeeker.getCv().setImage(null);
		jobSeekerDao.save(jobSeeker);
		return new SuccessResult("Cv eklendi.");
	}

}
