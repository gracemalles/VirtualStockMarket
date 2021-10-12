package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeHistory;

import java.util.List;

public interface TradeDao {

    void buyTrade(Trade trade, Player player);

    void sellTrade(Trade trade, Stock stock);

    void createTrade(Trade trade);

    public List<TradeHistory> tradeHistory(int playerId);

}
