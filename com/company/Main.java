package com.company;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        BaseCustomerManager customerManager = new StarbucksCustomerManager(new MernisServiceAdapter());
        customerManager.Save(new Customer(1,"İsim","Soyad",new Date(1990, 5, 12),"11111111111"));

    }
}
