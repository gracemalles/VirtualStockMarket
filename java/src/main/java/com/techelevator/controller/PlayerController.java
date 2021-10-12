package com.techelevator.controller;

import com.techelevator.dao.PlayerDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class PlayerController {

    @Autowired
    private PlayerDao playerDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(path="/players", method = RequestMethod.GET)
    public List<Player> getAllPlayers() {
        List<Player> current = playerDao.getAllPlayers();
        for(Player player : current){
            player.setUsername(userDao.getUsernameById(player.getUser_id()));
        }

        return current;
    }

    @RequestMapping(path="/players/game/{game_id}", method = RequestMethod.GET)
    public List<Player> getPlayersByGameId(@PathVariable int game_id) {
        return playerDao.getPlayersByGame(game_id);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.GET)
    public Player getPlayerById(@PathVariable int id) {
        return playerDao.getPlayerById(id);
    }

    @RequestMapping(path="/players", method= RequestMethod.POST)
    public int createPlayer(@RequestBody Player player) {
        return playerDao.createPlayer(player);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.PUT)
    public void updatePlayer(@RequestBody Player player) {
        playerDao.updatePlayer(player);
    }

    @RequestMapping(path="/players/{id}", method=RequestMethod.DELETE)
    public void deletePlayer(@PathVariable int id) {
        playerDao.deletePlayer(id);
    }

    @RequestMapping(path="/game/{game_id}/player", method=RequestMethod.GET)
    public Player getCurrentPlayerByGame(@PathVariable int game_id, Principal principal) {
        return playerDao.getCurrentPlayerByGame(game_id, principal.getName());
    }

}
