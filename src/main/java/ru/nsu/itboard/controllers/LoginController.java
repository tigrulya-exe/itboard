package ru.nsu.itboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.services.UserService;

@RestController
public class LoginController {

    private static final String LOGIN_PATH = "/login";

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(LOGIN_PATH + "/{username}")
    public String login(@PathVariable String username){
        return userService.login(username);
    }

}
