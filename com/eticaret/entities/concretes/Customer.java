package com.eticaret.entities.concretes;

import com.eticaret.entities.abstracts.Member;
import com.eticaret.entities.abstracts.Person;

public class Customer extends Person implements Member{

	public Customer(String name, String surname, String email, String password) {
		super(name, surname, email, password);
	}
	
}
