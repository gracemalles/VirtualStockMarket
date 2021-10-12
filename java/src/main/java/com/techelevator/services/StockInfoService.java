package com.techelevator.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.techelevator.model.SearchInfo;
import com.techelevator.model.StockInfo;
import com.techelevator.model.StockInfoDataPoint;

import java.math.BigDecimal;
import java.util.List;

public interface StockInfoService {
    // returns top ten stocks in descending order from API
    List<StockInfo> getTopStocks();

    StockInfo getStockInfo(String stockSymbol);

    List<SearchInfo> getSearchInfo(String searchTerm);

    void getPortfolioValue(int playerId);

    List<StockInfoDataPoint> getHistoricalStockData(String symbol, String resolution, String from, String to);

}
