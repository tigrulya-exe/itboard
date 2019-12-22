package ru.nsu.itboard.util;

import ru.nsu.itboard.models.User;
import ru.nsu.itboard.models.UserTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static List<UserTo> toUserTOs(Collection<User> users){
        return users.stream().map(UserTo::new).collect(Collectors.toList());
    }
}
