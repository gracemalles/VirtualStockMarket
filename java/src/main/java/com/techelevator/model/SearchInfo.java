package com.techelevator.model;

public class SearchInfo {

    private String searchTerm;
    private String description;
    private String stockSymbol;

    public SearchInfo(String searchTerm, String description, String stockSymbol) {
        this.searchTerm = searchTerm;
        this.description = description;
        this.stockSymbol = stockSymbol;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
}
