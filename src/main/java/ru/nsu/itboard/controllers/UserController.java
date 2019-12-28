package ru.nsu.itboard.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.services.UserService;
import ru.nsu.itboard.util.UserFilterContext;

import java.util.Collection;
import java.util.List;

@RestController
@ComponentScan(value = "ru.nsu.itboard.exceptions")
public class UserController {
    private static final String USER_PATH = "/users";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(USER_PATH + "/{id}")
    @ApiOperation(value = "Получение информации о пользователе")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @DeleteMapping(USER_PATH + "/{id}")
    @ApiOperation(value = "Удаление пользователя")
    public void removeUser(@PathVariable String id) {
        userService.removeUser(id);
    }

    @PostMapping(USER_PATH)
    @ApiOperation(value = "Добавление пользователя")
    public User addUser(@RequestBody User userWithoutId) {
        return userService.addUser(userWithoutId);
    }

    @GetMapping(USER_PATH + "/{id}/subscribers")
    @ApiOperation(value = "Получение подписчиков пользователя")
    public List<UserTo> getSubscribers(@PathVariable String id) {
        return userService.getSubscribers(id);
    }

    @GetMapping(USER_PATH + "/{id}/subscriptions")
    @ApiOperation(value = "Получение подписок пользователя")
    public List<UserTo> getSubscriptions(@PathVariable String id) {
        return userService.getSubscriptions(id);
    }

    @GetMapping(USER_PATH + "/search")
    @ApiOperation(value = "Поиск пользователя")
    public Collection<User> searchUsers(
            @RequestBody UserFilterContext filterContext) {
        return userService.getUsers(filterContext);
    }

    @PostMapping(USER_PATH + "/{id}")
    @ApiOperation(value = "Обновление информации о пользователе")
    public User updateUser(@PathVariable String id,
            @RequestBody User userWithoutId) {
        return userService.updateUser(id, userWithoutId);
    }

    @PostMapping(USER_PATH + "/{id}/subscribe?to={subscribeToId}")
    @ApiOperation(value = "Подписка на пользователя")
    public void subscribeUser(@PathVariable String id,
                                           @PathVariable String subscribeToId) {
        userService.subscribeUser(id, subscribeToId);
    }

    @PostMapping(USER_PATH + "/{id}/unsubscribe?from={unsubscribeToId}")
    @ApiOperation(value = "Отписка от пользователя")
    public void unsubscribeUser(@PathVariable String id,
                                           @PathVariable String unsubscribeToId) {
        userService.unsubscribeUser(id, unsubscribeToId);
    }
}
