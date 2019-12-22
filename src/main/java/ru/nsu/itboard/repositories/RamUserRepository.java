package ru.nsu.itboard.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.exceptions.WrongArgumentException;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.util.UserFilterContext;

import java.util.*;

@Repository
public class RamUserRepository implements UserRepository {
    private Map<String, User> users = new HashMap<>();

    private User checkUser(String userId){
        User user = users.get(userId);
        if(user == null)
            throw new NotFoundException("Wrong userId");
        return user;
    }

    @Override
    public User getUser(String userId) {
        return checkUser(userId);
    }

    @Override
    public void addUser(User user) {
        if(user == null)
            throw new WrongArgumentException("Wrong user");
        users.put(user.getId(), user);
    }

    @Override
    public void removeUser(String userId) {
        checkUser(userId);
        users.remove(userId);
    }

    @Override
    public void updateUser(String userId, User user) {
        checkUser(userId);
        users.put(userId, user);
    }

    @Override
    public Collection<User> getUsers(UserFilterContext filterContext) {
        return users.values();
    }
}
