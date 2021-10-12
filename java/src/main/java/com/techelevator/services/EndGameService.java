package com.techelevator.services;

import com.techelevator.dao.*;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class EndGameService implements Runnable  {

    private Game game;

    @Autowired
    private UserDao userDao;
    @Autowired
    private GameDao gameDao;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private TradeDao tradeDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private StockInfoService stockInfoService;
    @Autowired
    private SchedulingService schedulingService;

    @Override
    public void run() {
        List<Player> players = playerDao.getPlayersByGame(game.getId());
        for (Player player : players) {
            if(player.getGame_status().equals("Accepted") || player.getGame_status().equals("Finished")) {
                player.setGame_status("Finished");

                List<Stock> stocks = stockDao.getStocksByPlayerId(player.getId());
                for (Stock stock : stocks) {
                    Trade trade = new Trade(stock.getId(), stock.getTotal_shares(), "sell",
                            stockInfoService.getStockInfo(stock.getStock_ticker()).getCurrentPrice(), "shares",
                            LocalDate.now(), LocalTime.now());
                    stock.setTotal_shares(stock.getTotal_shares() -trade.getShares_traded());
                    player.setAvailable_funds(player.getAvailableFunds().
                            add(trade.getPrice().
                                    multiply(new BigDecimal(trade.getShares_traded()).setScale(2, RoundingMode.HALF_UP))));
                    stockDao.updateStock(stock);
                    tradeDao.createTrade(trade);
                    schedulingService.removeTaskFromScheduler(game.getId());

                }
                playerDao.updatePlayer(player);
            }
            else {
                player.setGame_status("Rejected");
                playerDao.updatePlayer(player);
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String toString() {
        return game.getName();
    }


}
