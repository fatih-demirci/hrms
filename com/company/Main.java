package com.company;

import com.company.business.concretes.GameManager;
import com.company.business.concretes.GameSell;
import com.company.business.concretes.UsersManager;
import com.company.core.abstracts.IUserCheckAdapter;
import com.company.core.concretes.UserCheckAdapter;
import com.company.core.concretes.UserCheckMernis;
import com.company.dataAccess.concretes.GameDao;
import com.company.dataAccess.concretes.Users;
import com.company.entities.abstracts.User;
import com.company.entities.concretes.Gamer;
import com.company.entities.concretes.Game;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Users users = new Users();
        GameSell gameSell = new GameSell();
        GameDao gameDao = new GameDao();




        Gamer gamer = new Gamer(1,
                "asd@hotmail.com",
                "111222",
                "11111111111",
                "İsim",
                "Soyisim",
                new Date(1990,10,25));


        Game game = new Game(1000,"Oyun adı",100);
        IUserCheckAdapter userCheckAdapter = new UserCheckAdapter();
        IUserCheckAdapter userCheckMernis = new UserCheckMernis();
        GameManager gameManager = new GameManager(gameDao);
        gameManager.add(game);

        System.out.println(gameManager.getGame(1000).getName());

        UsersManager usersManager = new UsersManager(users,userCheckMernis);
        usersManager.register(gamer);

        if(usersManager.getList().size()>0){
            List<User> persons = usersManager.getList();

            System.out.println("Üyeler");
            for (User user : persons) {
                System.out.println(user.getEmail());
            }

            gameManager.getGame(1000).setPercentageOfDiscount(20);
            gameSell.buy(persons.get(0), gameManager.getGame(1000));
            game.removeDiscount();
        }




    }



}
