package com.eticaret.core.concretes;

import com.eticaret.core.abstracts.SingExternal;

import google_signin.SingIn;

public class SingGoogleServiceAdapter implements SingExternal{
	

	
public boolean signIn(String email, String password) {
	
	SingIn google = new SingIn();	
	
		return google.signIn(email, password);
		
	}


}
