package com.techelevator.dao;

import com.techelevator.model.PlayerHistory;

import java.util.List;

public interface PlayerHistoryDao {

    List<PlayerHistory> getHistoryById(int playerId);

    int createPlayerHistory(int playerId);
}
