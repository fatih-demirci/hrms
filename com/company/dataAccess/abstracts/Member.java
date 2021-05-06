package com.company.dataAccess.abstracts;


import com.company.entities.abstracts.User;

import java.util.List;


public interface Member {
    void register(User user);
    void delete(int id);
    List<User> getList();
}
