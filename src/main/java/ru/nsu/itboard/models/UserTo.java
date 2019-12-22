package ru.nsu.itboard.models;

import lombok.Data;

@Data
public class UserTo {
    private String id;

    private String name;

    private int subscribersCount;

    private int subscriptionsCount;

    private boolean isPrivateProfile;

    public UserTo(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.subscribersCount = user.getSubscribers().size();
        this.subscriptionsCount = user.getSubscriptions().size();
        this.isPrivateProfile = user.isPrivateProfile();
    }
}
