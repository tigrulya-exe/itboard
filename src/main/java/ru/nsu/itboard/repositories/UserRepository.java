package ru.nsu.itboard.repositories;

import org.springframework.stereotype.Repository;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.util.UserFilterContext;

import java.util.List;

@Repository
public interface UserRepository {
    User getUser(String userId);

    void addUser(User user);

    void removeUser(String userId);

    void updateUser(String userId, User user);

    List<User> getUsers(UserFilterContext filterContext);
}