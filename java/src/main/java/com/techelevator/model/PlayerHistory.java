package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlayerHistory {

    private int id;
    private int playerId;
    private LocalDate localDate;
    private LocalTime localTime;
    private BigDecimal portfolioValue;
    private String username;

    public PlayerHistory(int id, int playerId, LocalDate localDate, LocalTime localTime, BigDecimal portfolioValue, String username) {
        this.id = id;
        this.playerId = playerId;
        this.localDate = localDate;
        this.localTime = localTime;
        this.portfolioValue = portfolioValue;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public BigDecimal getPortfolioValue() {
        return portfolioValue;
    }

    public void setPortfolioValue(BigDecimal portfolioValue) {
        this.portfolioValue = portfolioValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
