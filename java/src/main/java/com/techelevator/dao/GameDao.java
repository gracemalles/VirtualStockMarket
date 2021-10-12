package com.techelevator.dao;

import com.techelevator.model.Game;

import java.security.Principal;
import java.util.List;

public interface GameDao {

    List<Game> getAllGames();

    List<Game> getGamesByOrganizerId(int organizer_id);

    List<Game> getGamesByUsername(String username);

    Game getGameById(int id);

    int createGame(Game game);

    void deleteGame(int id);

}
