package com.company.entities.abstracts;

import java.util.Date;

public abstract class User {

    private int id;
    private String email;
    private String password;
    private String identificationNumber;
    private String name;
    private String surname;
    private Date dateOfBirth;

    public User(int id, String email, String password, String identificationNumber, String name, String surname, Date dateOfBirth) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.identificationNumber = identificationNumber;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
