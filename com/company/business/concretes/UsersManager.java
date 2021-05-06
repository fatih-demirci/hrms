package com.company.business.concretes;

import com.company.business.abstracts.IUsersManager;
import com.company.core.abstracts.IUserCheckAdapter;
import com.company.dataAccess.abstracts.Member;
import com.company.entities.abstracts.User;

import java.util.List;

public class UsersManager implements IUsersManager {

    private Member member;
    private IUserCheckAdapter userCheckAdapter;

    public UsersManager(Member member,IUserCheckAdapter userCheckAdapter){
        this.member=member;
        this.userCheckAdapter=userCheckAdapter;
    }

    public void register(User user){

        if (user.getPassword().length()>=6&& user.getEmail().indexOf("@")!=-1&&userCheckAdapter.CheckIfRealPerson(user)){
            member.register(user);
            System.out.println(user.getEmail()+" Kayıt yapıldı");
            return;
        }
        System.out.println("Kayıt Yapılamadı ");
    }

    @Override
    public void delete(int id) {
        if(id>=0){
            member.delete(id);
        }
    }

    @Override
    public List<User> getList() {
            return member.getList();
    }
}
