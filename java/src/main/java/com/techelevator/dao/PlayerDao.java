package com.techelevator.dao;

import com.techelevator.model.Player;

import java.util.List;

public interface PlayerDao {

    List<Player> getAllPlayers();

    List<Player> getPlayersByGame(int game_id);

    Player getPlayerById(int id);

    int createPlayer(Player player);

    void updatePlayer(Player player);

    void deletePlayer(int id);

    Player getCurrentPlayerByGame(int game_id, String username);

}
