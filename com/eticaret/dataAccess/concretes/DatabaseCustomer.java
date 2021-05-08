package com.eticaret.dataAccess.concretes;

import java.util.ArrayList;
import java.util.List;

import com.eticaret.dataAccess.abstracts.Database;
import com.eticaret.entities.abstracts.Member;
import com.eticaret.entities.concretes.Customer;

public class DatabaseCustomer implements Database{
	List<Customer> customers;
	
	
	
	public DatabaseCustomer() {
		customers = new ArrayList<Customer>();
	}
	
    public void register(Customer customer) {
		customers.add(customer);
		
	}

	public List<Customer> getCustomers() {
		return customers;
	}   
    
}
