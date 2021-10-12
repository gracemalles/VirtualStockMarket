package com.techelevator.model;

import java.math.BigDecimal;

public class StockInfo {
    private String stockSymbol;
    private String companyName;
    private BigDecimal currentPrice;
    private String logoURL;
    private BigDecimal percentageChange;

    public StockInfo(String stockSymbol, String companyName, BigDecimal currentPrice, String logoURL, BigDecimal percentageChange) {
        this.stockSymbol = stockSymbol;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.logoURL = logoURL;
        this.percentageChange = percentageChange;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCompanyName() {return companyName;}

    public void setCompanyName(String companyName) {this.companyName = companyName;}

    public String getLogoURL() {return logoURL;}

    public void setLogoURL(String logoURL) {this.logoURL = logoURL;}

    public BigDecimal getPercentageChange() {return percentageChange;}

    public void setPercentageChange(BigDecimal percentageChange) {this.percentageChange = percentageChange;}
}
