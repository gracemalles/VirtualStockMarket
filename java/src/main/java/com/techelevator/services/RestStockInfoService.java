package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.StockDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestStockInfoService implements StockInfoService {

    private String apiKey = "c55kruaad3icdhg123f0";
    private String BASE_URL = "https://finnhub.io/api/v1/";
    private Map<String, LocalTime> retrieveTimeMap = new HashMap<>();
    private Map<String, StockInfo> stockInfoMap = new HashMap<>();
    private Map <String, List<SearchInfo>> searchInfoMap = new HashMap<>();
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private StockDao stockDao;

    @Override
    public List<StockInfo> getTopStocks() {
        //TODO
        List<StockInfo> topTen = new ArrayList<>();
        topTen.add(getStockInfo("FB"));
        topTen.add(getStockInfo("TSLA"));
        topTen.add(getStockInfo("AAPL"));
        topTen.add(getStockInfo("AMZN"));
        topTen.add(getStockInfo("MSFT"));
        topTen.add(getStockInfo("NVDA"));
        topTen.add(getStockInfo("FDX"));
        topTen.add(getStockInfo("NFLX"));
        topTen.add(getStockInfo("KO"));
        topTen.add(getStockInfo("TGT"));
        return topTen;
    }

    @Override
    public StockInfo getStockInfo(String stockSymbol) {
        LocalTime retrievedTime = retrieveTimeMap.get(stockSymbol);
        if (retrievedTime != null) {
            if (ChronoUnit.MINUTES.between(retrievedTime, LocalTime.now()) < 10) {
                return stockInfoMap.get(stockSymbol);
            }
            else {
                retrieveTimeMap.remove(stockSymbol);
                stockInfoMap.remove(stockSymbol);
            }
        }
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        String url = BASE_URL + "/quote?symbol=" + stockSymbol + "&token=" + apiKey;
        StockInfo stockInfo = null;
        String price = "";
        String percentChange = "";
        String companyName = "";
        String logoURL = "";
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(result.getBody());
            JsonNode root = jsonNode.path("data");
            price = jsonNode.path("c").asText();
            percentChange = jsonNode.path("dp").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        url = BASE_URL + "/stock/profile2?symbol=" + stockSymbol + "&token=" + apiKey;
        result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        try {
            JsonNode jsonNode = objectMapper.readTree(result.getBody());
            JsonNode root = jsonNode.path("data");
            companyName = jsonNode.path("name").asText();
            logoURL = jsonNode.path("logo").asText();
        } catch (JsonProcessingException e) {e.printStackTrace();}
        stockInfo = new StockInfo(stockSymbol, companyName, new BigDecimal(price).setScale(2, RoundingMode.HALF_UP), logoURL, new BigDecimal(percentChange).setScale(2, RoundingMode.HALF_UP));

        retrieveTimeMap.put(stockInfo.getStockSymbol(), LocalTime.now());
        stockInfoMap.put(stockSymbol, stockInfo);
        return stockInfo;
    }

    public List<SearchInfo> getSearchInfo(String searchTerm) {
        List<SearchInfo> searchInfoList = new ArrayList<>();
        if (searchInfoMap.containsKey(searchTerm)) {
            return searchInfoMap.get(searchTerm);
        }
        HttpEntity<String> httpEntity = new HttpEntity<>("");
        String url = BASE_URL + "search?q=" + searchTerm + "&token=" + apiKey;
        String description = "";
        String stockSymbol = "";
        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(result.getBody());
            JsonNode root = jsonNode.path("data");
            System.out.println(jsonNode.asText());
            int count;
            if (Integer.parseInt(jsonNode.path("count").asText()) > 10) count = 10;
            else count = Integer.parseInt(jsonNode.path("count").asText());
            for (int i = 0; i < count; i++) {
                description = jsonNode.path("result").get(i).path("description").asText();
                stockSymbol = jsonNode.path("result").get(i).path("displaySymbol").asText();
                searchInfoList.add(new SearchInfo(searchTerm, description, stockSymbol));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return searchInfoList;
    }

    @Override
    public void getPortfolioValue(int playerId) {
        Player player = playerDao.getPlayerById(playerId);
        BigDecimal availableFunds = player.getAvailableFunds();
        List<Stock> stocks = stockDao.getStocksByPlayerId(playerId);
        BigDecimal stocksValue = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (Stock stock : stocks) {
            StockInfo stockInfo = getStockInfo(stock.getStock_ticker());
            BigDecimal thisStockValue = stockInfo.getCurrentPrice().multiply(new BigDecimal(stock.getTotal_shares()).setScale(2, RoundingMode.HALF_UP));
            stocksValue = stocksValue.add(thisStockValue);
        }
    }

    @Override
    public List<StockInfoDataPoint> getHistoricalStockData(String symbol, String resolution, String from, String to) {
        String url = BASE_URL + "stock/candle?symbol=" + symbol + "&resolution=" + resolution + "&from=" + from +
                "&to=" + to + "&token=" + apiKey;

        HttpEntity<String> httpEntity = new HttpEntity<>("");

        ResponseEntity<String> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                httpEntity,
                String.class
        );

        List<StockInfoDataPoint> data = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(result.getBody());
            JsonNode root = jsonNode.path("c");
            for(int i = 0; i < root.size(); i++) {
                double value = root.path(i).asDouble();
                long timestamp = jsonNode.path("t").path(i).asLong();
                StockInfoDataPoint dp = new StockInfoDataPoint(value, timestamp);
                data.add(dp);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }


}
