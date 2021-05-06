package com.company.core.concretes;

import com.company.entities.abstracts.User;

public class UserCheckAdapter implements com.company.core.abstracts.IUserCheckAdapter {
    public boolean CheckIfRealPerson(User user){
        return true;
    }
}
