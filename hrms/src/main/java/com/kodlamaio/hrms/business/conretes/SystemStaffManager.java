package com.kodlamaio.hrms.business.conretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.hrms.business.abstracts.SystemStaffService;
import com.kodlamaio.hrms.core.mailvalidate.MailValidService;
import com.kodlamaio.hrms.core.utilities.result.DataResult;
import com.kodlamaio.hrms.core.utilities.result.ErrorResult;
import com.kodlamaio.hrms.core.utilities.result.Result;
import com.kodlamaio.hrms.core.utilities.result.SuccessDataResult;
import com.kodlamaio.hrms.core.utilities.result.SuccessResult;
import com.kodlamaio.hrms.dataAccess.abstracts.SystemStaffDao;
import com.kodlamaio.hrms.entities.conretes.Member;
import com.kodlamaio.hrms.entities.conretes.SystemStaff;

@Service
public class SystemStaffManager implements SystemStaffService{
SystemStaffDao systemStaffDao;
MailValidService mailValidService;
	
	@Autowired
	public SystemStaffManager(SystemStaffDao systemStaffDao,
			MailValidService mailValidService){
		this.systemStaffDao=systemStaffDao;
		this.mailValidService=mailValidService;
	}

	@Override
	public Result add(SystemStaff systemStaff) {
		String error = "";
		boolean isMailValid=true;
		boolean isPassValid=true;
		boolean isNameValid=true;
			SystemStaff exSystemStaff = systemStaffDao.findById(systemStaff.getId());
			
			if(exSystemStaff==null) {
				exSystemStaff=new SystemStaff();
				exSystemStaff.setMember(new Member());
			}
			
				if(systemStaff.getName()!=null&&!systemStaff.getName().isEmpty()) {
					exSystemStaff.setName(systemStaff.getName());
				}else {
					error+=" İsim Boş olamaz";
					isNameValid=false;
				}
				if(systemStaff.getLastName()!=null&&!systemStaff.getLastName().isEmpty()) {
					exSystemStaff.setLastName(systemStaff.getLastName());
				}else {
					error+=" Soyad Boş olamaz";
					isNameValid=false;
				}
				if(systemStaff.getMember()!=null) {
					if(systemStaff.getMember().getEMail()!=null) {
						if(mailValidService.mailIsValid(systemStaff.getMember().getEMail())) {
							exSystemStaff.getMember().setEMail(systemStaff.getMember().getEMail());
						}else {
							isMailValid=false;
							error+=" Geçersiz mail adresi";
						}		
					}
					if(systemStaff.getMember().getPassword()!=null&&systemStaff.getMember().getPasswordRepeat()!=null) {
						if(systemStaff.getMember().getPassword().equals(systemStaff.getMember().getPasswordRepeat())) {
							exSystemStaff.getMember().setPassword(systemStaff.getMember().getPassword());
							exSystemStaff.getMember().setPasswordRepeat(systemStaff.getMember().getPasswordRepeat());
						}else {
							isPassValid=false;
							error+=" Şifreler Uyuşmuyor";
						}
					}
					
				}
			
				if(isMailValid&&isPassValid&&isNameValid) {
				if(exSystemStaff.getName()!=null&&exSystemStaff.getLastName()!=null&&exSystemStaff.getMember().getEMail()!=null&&exSystemStaff.getMember().getPassword()!=null) {
					systemStaffDao.save(exSystemStaff);
				}else {
					return new ErrorResult("Tüm alanlar zorunlu");
				}
				}else {
					return new ErrorResult(error);
				}
		
		
		return new SuccessResult("Başarılı");
	}
	
	public DataResult<SystemStaff> getSystemStaff(int id){
		return new SuccessDataResult<SystemStaff>(systemStaffDao.findById(id),"Sistem personeli getirildi");
	}
}
