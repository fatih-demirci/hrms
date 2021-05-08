package com.eticaret.core.concretes;

import com.eticaret.core.abstracts.SingExternal;

public class SingExternalAdapter implements SingExternal{

	public boolean signIn(String email, String password) {
		
		if(!email.isEmpty()&&!password.isEmpty()) {
			System.out.println("Giri� Ba�ar�l�");
			return true;
		}
		System.out.println("E-mail ve parola giriniz.");
		return false;
	}

}
