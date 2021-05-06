package com.company.business.concretes;

import com.company.business.abstracts.IGameManager;
import com.company.dataAccess.concretes.GameDao;
import com.company.entities.concretes.Game;

public class GameManager implements IGameManager {

    GameDao gameDao;


    public GameManager( GameDao gameDao){
        this.gameDao=gameDao;
    }
    @Override
    public void add(Game game) {
        if (game.getName().length()>0){
            gameDao.add(game);
        }
    }

    @Override
    public void delete(int id) {
        if (id>0){
            gameDao.delete(id);
        }

    }

    @Override
    public Game getGame(int id) {
        return gameDao.getGame(id);
    }
}
