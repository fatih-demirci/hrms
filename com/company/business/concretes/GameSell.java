package com.company.business.concretes;

import com.company.business.abstracts.Sell;
import com.company.entities.abstracts.Entity;
import com.company.entities.abstracts.User;

public class GameSell implements Sell {
    public void buy(User user, Entity entity){
        System.out.println(user.getEmail()+" "+entity.getName()+" ürününü "+ entity.getPrice() +" fiyatına satın aldı");
    }
}
