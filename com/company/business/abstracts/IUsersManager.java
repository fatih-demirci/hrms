package com.company.business.abstracts;

import com.company.entities.abstracts.User;

import java.util.List;

public interface IUsersManager {
    void register(User user);
    void delete(int id);
    List<User> getList();
}
