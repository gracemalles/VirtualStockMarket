package com.techelevator.controller;

import com.techelevator.Application;
import com.techelevator.dao.GameDao;
import com.techelevator.model.Game;
import com.techelevator.services.EndGameService;
import com.techelevator.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

@RestController
@CrossOrigin
//@PreAuthorize("isAuthenticated()")
public class GameController {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private EndGameService endGameService;

    @RequestMapping(path="/games/organizer/{organizer_id}", method = RequestMethod.GET)
    public List<Game> getGamesByOrganizerId(@PathVariable int organizer_id) { return gameDao.getGamesByOrganizerId(organizer_id); }

    @RequestMapping(path="/games", method = RequestMethod.GET)
    public List<Game> getGamesByUsername(Principal principal) {
        return gameDao.getGamesByUsername(principal.getName()); }

    @RequestMapping(path="/games/{id}", method = RequestMethod.GET)
    public Game getGameById(@PathVariable int id) { return gameDao.getGameById(id); }

    @RequestMapping(path="/games", method = RequestMethod.POST)
    public int createGame(@RequestBody Game game) {
        System.out.println(game.getEnd_date());
        System.out.println(game.getEnd_time());
        int gameId = gameDao.createGame(game);
        game.setId(gameId);
        LocalDateTime dateTime =  LocalDateTime.of(game.getEnd_date(), game.getEnd_time());
        Date date = Date.from(dateTime.atZone(TimeZone.getTimeZone("UTC").toZoneId()).toInstant());
        endGameService.setGame(game);
        schedulingService.addTaskToScheduler(game.getId(), endGameService, date);
        System.out.println(Date.from(Instant.now()).getTime() - date.getTime());
        return gameId; }

    @RequestMapping(path="/games/{id}", method = RequestMethod.DELETE)
    public void deleteGame(@PathVariable int id) { gameDao.deleteGame(id); }
}
