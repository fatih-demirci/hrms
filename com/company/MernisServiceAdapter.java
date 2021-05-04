package com.company;

import MernisService.BSLKPSPublicSoap;

import java.util.Locale;

public class MernisServiceAdapter implements CustomerCheckService{

    @Override
    public boolean CheckIfRealPerson(Customer customer) {
        BSLKPSPublicSoap client = new BSLKPSPublicSoap();
        try {
            return client.TCKimlikNoDogrula(Long.valueOf(customer.getNationalityId()),
                    customer.getFirstName().toUpperCase(),
                    customer.getLastName().toUpperCase(),
                    customer.getDateOfBirth().getYear());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
