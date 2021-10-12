package com.techelevator.controller;

import com.techelevator.dao.StockDao;
import com.techelevator.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class StockController {

    @Autowired
    private StockDao stockDao;

    @RequestMapping(path="/player/{player_id}/stocks", method= RequestMethod.GET)
    public List<Stock> getStocksByPlayerId(@PathVariable int player_id) {
        return stockDao.getStocksByPlayerId(player_id);
    }

    @RequestMapping(path="/stocks", method = RequestMethod.GET)
    public List<Stock> getAllStocks() {
        return stockDao.getAllStocks();
    }

    @RequestMapping(path="/player/{player_id}/stocks/{ticker}", method=RequestMethod.GET)
    public Stock getStockByPlayerAndTicker(@PathVariable int player_id, @PathVariable String ticker) {
        return stockDao.getStockByPlayerAndTicker(player_id, ticker);
    }

    @RequestMapping(path="/stocks", method = RequestMethod.POST)
    public void createPlayer(@RequestBody Stock stock) {
        stockDao.createStock(stock);
    }
}
