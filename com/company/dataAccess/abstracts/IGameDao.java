package com.company.dataAccess.abstracts;

import com.company.entities.abstracts.Entity;
import com.company.entities.concretes.Game;

public interface IGameDao {
    public void add(Game game);
    public void delete(int id);
    public Game getGame(int id);
}
