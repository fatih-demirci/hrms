package com.company;


public abstract class BaseCustomerManager implements CustomerService{

    @Override
    public void Save(Customer customer) {
        System.out.println("Saves to db : "+ customer.getFirstName());
    }
    
}
