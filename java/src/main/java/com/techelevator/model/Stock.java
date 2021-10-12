package com.techelevator.model;

import java.math.BigDecimal;

public class Stock {
    private int id;
    private int player_id;
    private String stock_name;
    private String stock_ticker;
    private double total_shares;

    public Stock(int player_id, String stock_name, String stock_ticker, double total_shares) {
        this.player_id = player_id;
        this.stock_name = stock_name;
        this.stock_ticker = stock_ticker;
        this.total_shares = total_shares;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getStock_name() {
        return stock_name;
    }

    public void setStock_name(String stock_name) {
        this.stock_name = stock_name;
    }

    public double getTotal_shares() {
        return total_shares;
    }

    public void setTotal_shares(double total_shares) {
        this.total_shares = total_shares;
    }

    public String getStock_ticker() {
        return stock_ticker;
    }

    public void setStock_ticker(String stock_ticker) {
        this.stock_ticker = stock_ticker;
    }
}
