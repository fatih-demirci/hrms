package com.company.business.abstracts;

import com.company.entities.concretes.Game;

public interface IGameManager {
    public void add(Game game);
    public void delete(int id);
    public Game getGame(int id);
}
