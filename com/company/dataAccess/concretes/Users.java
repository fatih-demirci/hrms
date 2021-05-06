package com.company.dataAccess.concretes;

import com.company.dataAccess.abstracts.Member;
import com.company.entities.abstracts.User;

import java.util.ArrayList;
import java.util.List;

public class Users implements Member {
    List<User> users;

    public Users(){
        users = new ArrayList<User>();
    }

    @Override
    public void register(User user) {
        users.add(user);
    }

    @Override
    public void delete(int id) {
        for (User user:users) {
            if (user.getId()==id){
                users.remove(user);
                return;
            }
        }
    }

    @Override
    public List<User> getList() {
        return users;
    }
}
