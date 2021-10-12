package com.techelevator.dao;

import com.techelevator.model.Stock;

import java.util.List;

public interface StockDao {
    List<Stock> getStocksByPlayerId(int id);

    List<Stock> getAllStocks();

    Stock getStockByPlayerAndTicker(int player_id, String ticker);

    Stock getStockByStockId(int stockId);

    void updateStock(Stock stock);

    void createStock(Stock stock);
}
