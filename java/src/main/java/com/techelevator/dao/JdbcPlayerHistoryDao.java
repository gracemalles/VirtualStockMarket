package com.techelevator.dao;

import com.techelevator.model.Player;
import com.techelevator.model.PlayerHistory;
import com.techelevator.model.Stock;
import com.techelevator.model.StockInfo;
import com.techelevator.services.StockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class JdbcPlayerHistoryDao implements PlayerHistoryDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private StockDao stockDao;
    @Autowired
    private StockInfoService stockInfoService;

    public List<PlayerHistory> getHistoryById(int playerId) {
        List<PlayerHistory> playerHistories = new ArrayList<>();
        String sql = "SELECT player_history.id, player_history.player_id, player_history.date, " +
                "player_history.time, player_history.portfolio_value, users.username " +
                "FROM player_history JOIN game_players ON " +
                "player_id = game_players.id JOIN users ON " +
                "game_players.user_id = users.user_id " +
                "WHERE player_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, playerId);
        while (result.next()) {
            playerHistories.add(mapRowToPlayerHistory(result));
        }
        return playerHistories;
    }

    @Scheduled(cron = "0 */10 9-15 * * *")
    public void createAllPlayerHistories() {
        List<Player> players = playerDao.getAllPlayers();
        for (Player player : players) {
                createPlayerHistory(player.getId());
        }
    }

    public int createPlayerHistory(int playerId) {
        BigDecimal portfolioValue = getPortfolioValue(playerId);
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String sql = "INSERT INTO player_history (player_id, date, time, portfolio_value) " +
                "VALUES (?, ?, ?, ?) RETURNING id;";
        return jdbcTemplate.queryForObject(sql, Integer.class, playerId, date, time, portfolioValue);
    }

    private BigDecimal getPortfolioValue(int playerId) {
        Player player = playerDao.getPlayerById(playerId);
        BigDecimal availableFunds = player.getAvailableFunds();
        List<Stock> stocks = stockDao.getStocksByPlayerId(playerId);
        BigDecimal stocksValue = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for (Stock stock : stocks) {
            StockInfo stockInfo = stockInfoService.getStockInfo(stock.getStock_ticker());
            BigDecimal thisStockValue = stockInfo.getCurrentPrice().multiply(new BigDecimal(stock.getTotal_shares()).setScale(2, RoundingMode.HALF_UP));
            stocksValue = stocksValue.add(thisStockValue);
        }
        return stocksValue.add(availableFunds);
    }

    private PlayerHistory mapRowToPlayerHistory(SqlRowSet rowSet) {

        int id = rowSet.getInt(1);
        int playerId = rowSet.getInt(2);
        LocalDate date = rowSet.getDate(3).toLocalDate();
        LocalTime time = rowSet.getTime(4).toLocalTime();
        BigDecimal portfolioValue = rowSet.getBigDecimal(5);
        String username = rowSet.getString(6);

        return new PlayerHistory(id, playerId, date, time, portfolioValue, username);
    }
}
