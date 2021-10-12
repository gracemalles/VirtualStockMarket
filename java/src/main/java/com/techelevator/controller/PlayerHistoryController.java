package com.techelevator.controller;

import com.techelevator.dao.PlayerHistoryDao;
import com.techelevator.model.PlayerHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PlayerHistoryController {

    @Autowired
    PlayerHistoryDao playerHistoryDao;

    @RequestMapping(path = "/players/{id}/history", method = RequestMethod.GET)
    List<PlayerHistory> getPlayerHistories(@PathVariable int id) {
        return playerHistoryDao.getHistoryById(id);
    }
}
