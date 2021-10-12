package com.techelevator.dao;

import com.techelevator.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPlayerDao implements PlayerDao {

    private JdbcTemplate jdbcTemplate;
    private JdbcUserDao userDao;

    public JdbcPlayerDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    @Override
    public List<Player> getAllPlayers() {
        String sql = "SELECT id, user_id, game_id, available_funds, game_status " +
                "FROM game_players";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Player> players = new ArrayList<>();

        while(results.next()) {
            players.add(mapResultToPlayer(results));

        }


        return players;
    }

    @Override
    public List<Player> getPlayersByGame(int game_id) {
        String sql = "SELECT id, game_players.user_id, game_id, available_funds, game_status, username " +
                "FROM game_players JOIN users ON users.user_id = game_players.user_id " +
                "WHERE game_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, game_id);
        List<Player> players = new ArrayList<>();

        while(results.next()) {
            Player player = mapResultToPlayer(results);
            player.setUsername(results.getString("username"));
            players.add(player);
        }

        return players;
    }

    @Override
    public Player getPlayerById(int id) {
        String sql = "SELECT id, user_id, game_id, available_funds, game_status " +
                "FROM game_players " +
                "WHERE id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        Player player = null;

        if(result.next()) {
            player = mapResultToPlayer(result);
        }

        return player;
    }

    @Override
    public int createPlayer(Player player) {
        String sql = "INSERT INTO game_players (user_id, game_id, available_funds, game_status) " +
                "VALUES (?, ?, ?, ?) Returning id";

        return jdbcTemplate.queryForObject(sql, Integer.class, player.getUser_id(), player.getGame_id(), player.getAvailableFunds(), player.getGame_status());
    }

    @Override
    public void updatePlayer(Player player) {
        String sql = "UPDATE game_players " +
                "SET available_funds = ?, game_status = ? " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, player.getAvailableFunds(), player.getGame_status(), player.getId());

    }

    @Override
    public void deletePlayer(int id) {
        String sql = "DELETE FROM game_players " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    @Override
    public Player getCurrentPlayerByGame(int game_id, String username) {
        String sql = "SELECT * " +
                "FROM game_players " +
                "JOIN users ON users.user_id = game_players.user_id " +
                "WHERE game_id = ? AND users.username = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, game_id, username);
        Player player = null;
        if(result.next()) {
            player = mapResultToPlayer(result);
        }

        return player;
    }


    private Player mapResultToPlayer(SqlRowSet result) {
        int id = result.getInt("id");
        Long user_id = result.getLong("user_id");
        int game_id = result.getInt("game_id");
        String funds = result.getString("available_funds");
        String gameStatus = result.getString("game_status");

        Player player = new Player(id, user_id, game_id, gameStatus);
        if(funds != null) {
            player.setAvailable_funds(new BigDecimal(funds));
        }
        return player;
    }
}
