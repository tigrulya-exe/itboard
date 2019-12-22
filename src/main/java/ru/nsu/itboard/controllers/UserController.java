package ru.nsu.itboard.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.services.UserService;
import ru.nsu.itboard.util.UserFilterContext;

import java.util.List;

@RestController
public class UserController {
    private static final String USER_PATH = "/usr";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(USER_PATH + "/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @DeleteMapping(USER_PATH + "/{id}")
    public void removeUser(@PathVariable String id) {
        userService.removeUser(id);
    }

    @PostMapping(USER_PATH)
    public User addUser(@RequestBody User userWithoutId) {
        return userService.addUser(userWithoutId);
    }

    @GetMapping(USER_PATH + "/{id}/sbrs")
    public List<UserTo> getSubscribers(@PathVariable String id) {
        return userService.getSubscribers(id);
    }

    @GetMapping(USER_PATH + "/{id}/sbps")
    public List<UserTo> getSubscriptions(@PathVariable String id) {
        return userService.getSubscriptions(id);
    }

    @GetMapping(USER_PATH + "/search")
    public List<User> searchUsers(
            @RequestBody UserFilterContext filterContext) {
        return userService.getUsers(filterContext);
    }

    @PostMapping(USER_PATH + "/{id}")
    public User addUser(@PathVariable String id,
            @RequestBody User userWithoutId) {
        return userService.updateUser(id, userWithoutId);
    }

    @PostMapping(USER_PATH + "/{id}/sbr/{subscribeToId}")
    public void subscribeUser(@PathVariable String id,
                                           @PathVariable String subscribeToId) {
        userService.subscribeUser(id, subscribeToId);
    }

    @PostMapping(USER_PATH + "/{id}/usbr/{unsubscribeToId}")
    public void unsubscribeUser(@PathVariable String id,
                                           @PathVariable String unsubscribeToId) {
        userService.unsubscribeUser(id, unsubscribeToId);
    }


}
