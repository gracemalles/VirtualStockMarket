package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired UserDao userDao;

    @RequestMapping(value= "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable long id) {
        return userDao.getUserById(id);
    }



}
