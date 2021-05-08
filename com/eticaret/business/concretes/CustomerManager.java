package com.eticaret.business.concretes;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

import com.eticaret.business.abstracts.PersonCustomer;
import com.eticaret.core.abstracts.SingExternal;
import com.eticaret.dataAccess.abstracts.Database;
import com.eticaret.entities.abstracts.Member;
import com.eticaret.entities.concretes.Customer;

public class CustomerManager implements PersonCustomer{

private ValidateEmail validateEmail;
Database database;
SingExternal singExternal;

public CustomerManager(Database database) {
	this.database=database;
}


@Override
public void register(Customer customer) {
	validateEmail = new ValidateEmail();
	if(customer.getPassword().length()>=6
			&&validateEmail.stringValidate(customer.getEmail())
			&&customer.getName().length()>=2
			&&customer.getSurname().length()>=2) {
		List<Customer> customers = database.getCustomers();
		for(int i =0;i<customers.size();i++) {
			if(customers.get(i).getEmail()==customer.getEmail()) {
				System.out.println("E-mail adresi zaten kullanýlýyor");
				return;
			}	
		}		
		
		
	System.out.println("üyelik baþarýlý lütfen e-posta doðrulamasý yapýn");
	int validate =(int)(Math.random()*1000000);
	validateEmail.sendEmail(validate);
	Scanner input = new Scanner(System.in);
	int code=0;
	while(code!=validate) {
		System.out.println("Doðrulama kodunu giriniz.");
		code=input.nextInt();
		if(code==validate) {
			System.out.println("E-mail onayý gerçekleþtirildi");
			database.register(customer);
		}else {
			System.out.println("Gerçersiz doðrulama kodu");
		}
	}	
	
	return;
}
	System.out.println("Geçersiz bilgiler");

}

public void signIn(Database database,String email,String password) {
	List<Customer> customers =database.getCustomers();
	for (Customer customer : customers) {
		if(customer.getEmail()==email&&customer.getPassword()==password) {
			System.out.println("Giriþ baþarýlý");
		}else {
			System.out.println("E-mail adresi veya parola yanlýþ");
		}
	}	
}

public void signIn(SingExternal singExternal,String email,String password) {
	singExternal.signIn(email, password);

}




}

