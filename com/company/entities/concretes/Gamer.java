package com.company.entities.concretes;

import com.company.entities.abstracts.User;

import java.util.Date;

public class Gamer extends User {

    public Gamer(int id, String email, String password, String identificationNumber, String name, String surname, Date dateOfBirth) {
        super(id, email, password, identificationNumber, name, surname, dateOfBirth);
    }

}
