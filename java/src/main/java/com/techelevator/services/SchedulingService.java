package com.techelevator.services;


import com.techelevator.dao.GameDao;
import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.StockDao;
import com.techelevator.model.Game;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulingService  {

    @Autowired
    GameDao gameDao;
    @Autowired
    PlayerDao playerDao;
    @Autowired
    StockDao stockDao;
    @Autowired
    EndGameService endGameService;

    TaskScheduler scheduler;

    Map<Integer, ScheduledFuture<?>> jobsMap = new HashMap<>();

    public SchedulingService(TaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void addTaskToScheduler(int id, Runnable task, Date runningDate) {
        ScheduledFuture<?> scheduledTask = scheduler.schedule(task, runningDate);
        jobsMap.put(id, scheduledTask);
       // System.out.println(jobsMap.get(id).toString() + " Date: " + runningDate);
    }

    public void removeTaskFromScheduler(int id) {
        ScheduledFuture<?> scheduledTask = jobsMap.get(id);
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
            jobsMap.remove(id);
        }
    }

    @EventListener({ContextRefreshedEvent.class})
    void contextRefreshedEvent(){
        List<Game> games = gameDao.getAllGames();
        for (Game game : games ) {
            List<Player> players = playerDao.getPlayersByGame(game.getId());
            boolean isFinished = true;
            for (Player player : players) {
                if (player.getGame_status().equals("Accepted")) {
                    isFinished = false;
                }
            }
            if (!isFinished) {
                LocalDateTime dateTime = LocalDateTime.of(game.getEnd_date(), game.getEnd_time());
                Date date = Date.from(dateTime.atZone(TimeZone.getTimeZone("UTC").toZoneId()).toInstant());
              //  System.out.println(game.getId());
                endGameService.setGame(game);
                addTaskToScheduler(game.getId(), endGameService, date);
            }
        }
    }

}
