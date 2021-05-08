package com.eticaret.business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eticaret.business.abstracts.ValidateString;

public class ValidateEmail implements ValidateString{
	private String regex;
	Pattern pattern;
		
	@Override
	public boolean stringValidate(String email) {
		regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";	 
		pattern = Pattern.compile(regex);	 
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	public void sendEmail(int validate) {
		System.out.println(validate);
	}
	
	
		

}
