package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Trade {
    private int id;
    private int stock_id;
    private double shares_traded;
    private String buy_or_sell;
    private BigDecimal price;
    // trade entered in shares or dollars
    private String entered_in;
    private LocalDate date;
    private LocalTime time;

    public Trade(int stock_id, double shares_traded, String buy_or_sell, BigDecimal price, String entered_in, LocalDate date, LocalTime time) {
        this.stock_id = stock_id;
        this.shares_traded = shares_traded;
        this.buy_or_sell = buy_or_sell;
        this.price = price;
        this.entered_in = entered_in;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public double getShares_traded() {
        return shares_traded;
    }

    public void setShares_traded(double shares_traded) {
        this.shares_traded = shares_traded;
    }

    public String getBuy_or_sell() {
        return buy_or_sell;
    }

    public void setBuy_or_sell(String buy_or_sell) {
        this.buy_or_sell = buy_or_sell;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEntered_in() {
        return entered_in;
    }

    public void setEntered_in(String entered_in) {
        this.entered_in = entered_in;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
