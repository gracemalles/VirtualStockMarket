package com.techelevator.model;


import java.math.BigDecimal;

public class Player {
    private int id;
    private Long user_id;
    private int game_id;
    private BigDecimal available_funds;
    private String game_status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;


    public Player() {
        this.available_funds = new BigDecimal("100000.00");
    }

    public Player(Long user_id, int game_id, String game_status) {
        this.user_id = user_id;
        this.game_id = game_id;
        this.available_funds = new BigDecimal(100000);
        this.game_status = game_status;
    }

    public Player(int id, Long user_id, int game_id, String game_status) {
        this.id = id;
        this.user_id = user_id;
        this.game_id = game_id;
        this.game_status = game_status;
        this.available_funds = new BigDecimal(100000);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_status() {
        return this.game_status;
    }

    public void setGame_status(String game_status) {
        this.game_status = game_status;
    }

    public BigDecimal getAvailableFunds() {
        return available_funds;
    }

    public void setAvailable_funds(BigDecimal availableFunds) {
        this.available_funds = availableFunds;
    }
}
