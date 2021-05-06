package com.company.dataAccess.concretes;

import com.company.business.concretes.GameManager;
import com.company.dataAccess.abstracts.IGameDao;
import com.company.entities.abstracts.User;
import com.company.entities.concretes.Game;

import java.util.ArrayList;
import java.util.List;

public class GameDao implements IGameDao {
    List<Game> games;


    public GameDao(){
        games = new ArrayList<Game>();
    }

    @Override
    public void add(Game game) {
        games.add(game);
    }

    @Override
    public void delete(int id) {
        for (Game game:games) {
            if (game.getId()==id){
                games.remove(game);
                return;
            }
        }
    }

    @Override
    public Game getGame(int id) {
        for (Game game:games) {
            if (game.getId()==id){
                return game;
            }
        }
        return null;
    }
}
