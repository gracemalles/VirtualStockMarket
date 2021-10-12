package com.techelevator.dao;

import com.techelevator.model.Game;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGameDao implements GameDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcGameDao(DataSource datasource) {
        this.jdbcTemplate = new JdbcTemplate(datasource);
    }

    public List<Game> getAllGames() {
        String sql = "SELECT id, name, organizer_id, end_date, end_time " +
                "FROM games;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Game> games = new ArrayList<>();

        while(results.next()) {
            games.add(mapResultToGame(results));
        }

        return games;
    }

    @Override
    public List<Game> getGamesByOrganizerId(int organizer_id) {
        String sql = "SELECT id, name, organizer_id, end_date, end_time " +
                "FROM games " +
                "WHERE organizer_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, organizer_id);
        List<Game> games = new ArrayList<>();

        while(results.next()) {
            games.add(mapResultToGame(results));
        }

        return games;
    }

    @Override
    public List<Game> getGamesByUsername(String username) {
        String sql = "SELECT games.id, name, organizer_id, end_date, end_time " +
                "FROM games JOIN game_players ON games.id = game_players.game_id " +
                "JOIN users ON game_players.user_id = users.user_id " +
                "WHERE users.username = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        List<Game> games = new ArrayList<>();

        while(results.next()) {
            games.add(mapResultToGame(results));
        }

        return games;
    }

    @Override
    public Game getGameById(int id) {
        String sql = "SELECT id, name, organizer_id, end_date, end_time " +
                "FROM games " +
                "WHERE id = ?";

        Game game = null;
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);

        if(result.next()) {
            game = mapResultToGame(result);
        }

        return game;
    }

    @Override
    public int createGame(Game game) {
        String sql = "INSERT INTO games (name, organizer_id, end_date, end_time) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING id";

        int id = jdbcTemplate.queryForObject(sql, Integer.class, game.getName(), game.getOrganizer_id(), game.getEnd_date(), game.getEnd_time());
        return id;
    }

    @Override
    public void deleteGame(int id) {
        String sql = "DELETE FROM games " +
                "WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    private Game mapResultToGame(SqlRowSet result) {
        int id = result.getInt("id");
        String name = result.getString("name");
        int organizer_id = result.getInt("organizer_id");
        LocalDate end_date = result.getDate("end_date").toLocalDate();
        LocalTime end_time = result.getTime("end_time").toLocalTime();

        Game game = new Game(id, name, organizer_id, end_date, end_time);
        return game;
    }
}
