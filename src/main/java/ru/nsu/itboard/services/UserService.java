package ru.nsu.itboard.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.itboard.exceptions.NotFoundException;
import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;
import ru.nsu.itboard.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.nsu.itboard.util.Converter.*;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String username){
        try{
            User user = userRepository.getUserByLogin(username);
            return user.getId();
        } catch (NotFoundException e){
            User user = new User();
            user = addUser(user);
            return user.getId();
        }
    }

    public User getUser(String userId){
        return userRepository.getUser(userId);
    }

    public void removeUser(String userId) {
        userRepository.removeUser(userId);
    }

    public User addUser(User userWithoutId) {
        userWithoutId.generateId();
        userRepository.addUser(userWithoutId);
        return userWithoutId;
    }

    public List<UserTo> getSubscribers(String id) {
        Map<String, User>  users = userRepository.getUser(id).getSubscribers();
        return toUserTOs(users.values());
    }

    public List<UserTo> getSubscriptions(String id) {
        Map<String, User> users = userRepository.getUser(id).getSubscriptions();
        return toUserTOs(users.values());
    }

    public Collection<User> getUsers(String userName) {
        return userRepository.getUsers().stream()
                .filter(u -> userName.equals(u.getName()))
                .collect(Collectors.toList());
    }

    public User updateUser(String id, User userWithoutId) {
        userWithoutId.setId(id);
        userRepository.updateUser(id, userWithoutId);
        return userWithoutId;
    }

    public void subscribeUser(String id, String subscribeToId) {
        User user = userRepository.getUser(id);
        User userToSubscribe = userRepository.getUser(subscribeToId);
        user.getSubscriptions().put(id, userToSubscribe);
    }

    public void unsubscribeUser(String id, String unsubscribeToId) {
        User user = userRepository.getUser(id);
        Map<String, User> users = user.getSubscriptions();
        users.remove(unsubscribeToId);
    }
}
