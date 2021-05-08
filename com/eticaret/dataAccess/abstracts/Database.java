package com.eticaret.dataAccess.abstracts;

import java.util.List;
import com.eticaret.entities.concretes.Customer;

public interface Database {
	
	public void register(Customer customer);
	public List<Customer> getCustomers();
}
