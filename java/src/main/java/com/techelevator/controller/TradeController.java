package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.StockDao;
import com.techelevator.dao.TradeDao;
import com.techelevator.model.Player;
import com.techelevator.model.Stock;
import com.techelevator.model.Trade;
import com.techelevator.model.TradeHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class TradeController {

    @Autowired
    private TradeDao tradeDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private PlayerDao playerDao;

    @RequestMapping(path="/trade/buy", method = RequestMethod.POST)
    public void buyTrade(@RequestBody Trade trade, @RequestBody Player player) {
        tradeDao.buyTrade(trade, player);
    }

    @RequestMapping(path="/tradehistory/{playerId}", method = RequestMethod.GET)
    public List<TradeHistory> tradeHistory(@PathVariable int playerId) {
        return tradeDao.tradeHistory(playerId);
    }

    @RequestMapping(path="/trade/sell", method = RequestMethod.POST)
    public void sellTrade(@RequestBody Trade trade, @RequestBody Stock stock) {
        tradeDao.sellTrade(trade, stock);
    }

    @RequestMapping(path = "/trade", method = RequestMethod.POST)
    public void trade(@RequestBody Trade trade) {

        Stock stock =  stockDao.getStockByStockId(trade.getStock_id());

        Player player = playerDao.getPlayerById(stock.getPlayer_id());

        // if buying, make sure player has enough money
        if (trade.getBuy_or_sell().equalsIgnoreCase("buy") &&
                (player.getAvailableFunds().compareTo(trade.getPrice()) >= 0)){
            tradeDao.createTrade(trade);

            // TODO update player and stock
            // TODO validate sell transaction


            // update player's balance
            BigDecimal newBalance = player.getAvailableFunds().subtract(trade.getPrice());
            player.setAvailable_funds(newBalance);
            playerDao.updatePlayer(player);

            // update stock to reflect new number of shares
            double originalShares = stock.getTotal_shares();
            double newShares = trade.getShares_traded();
            stock.setTotal_shares(originalShares + newShares);
            stockDao.updateStock(stock);

        }
        else if(trade.getBuy_or_sell().equalsIgnoreCase("sell") && stock.getTotal_shares() >= trade.getShares_traded()) {
            tradeDao.createTrade(trade);

            // update player's balance
            BigDecimal newBalance = player.getAvailableFunds().add(trade.getPrice());
            player.setAvailable_funds(newBalance);
            playerDao.updatePlayer(player);

            // update stock to reflect new number of shares
            double originalShares = stock.getTotal_shares();
            double soldShares = trade.getShares_traded();
            stock.setTotal_shares(originalShares - soldShares);
            stockDao.updateStock(stock);
        }
    }

}
