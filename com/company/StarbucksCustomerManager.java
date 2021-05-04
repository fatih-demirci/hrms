package com.company;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StarbucksCustomerManager extends BaseCustomerManager {

    private CustomerCheckService customerCheckService;

    public StarbucksCustomerManager(CustomerCheckService customerCheckService) {
        this.customerCheckService = customerCheckService;
    }

    @Override
    public void Save(Customer customer) {
        if (customerCheckService.CheckIfRealPerson(customer)) {
            super.Save(customer);
        } else {
            try {
                throw new Exception("Not a valid person");
            } catch (Exception ex) {
                Logger.getLogger(StarbucksCustomerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
